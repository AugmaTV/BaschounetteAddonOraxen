package fr.augma.addonoraxen.mechanics.fertilizer;

import fr.augma.addonoraxen.Main;
import io.th0rgal.oraxen.compatibilities.CompatibilitiesManager;
import io.th0rgal.oraxen.compatibilities.provided.worldguard.WorldGuardCompatibility;
import io.th0rgal.oraxen.items.OraxenItems;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FertilizerMechanicManager implements Listener {

    private final MechanicFactory factory;
    private final WorldGuardCompatibility worldGuardCompatibility;

    public FertilizerMechanicManager(MechanicFactory factory) {
        this.factory = factory;
        if (CompatibilitiesManager.isCompatibilityEnabled("WorldGuard"))
            worldGuardCompatibility = (WorldGuardCompatibility) CompatibilitiesManager
                    .getActiveCompatibility("WorldGuard");
        else
            worldGuardCompatibility = null;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onblockInteract(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getClickedBlock() == null)
            return;

        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        if (item == null)
            return;

        String itemID = OraxenItems.getIdByItem(item);

        if (factory.isNotImplementedIn(itemID))
            return;

        FertilizerMechanic mechanic = (FertilizerMechanic) factory.getMechanic(itemID);

        Player player = e.getPlayer();

        if(!Main.get().getFertilizerManager().playerCanUse(player, mechanic))
            return;

        for(Block block : getNearbyBlocks(e.getClickedBlock().getLocation(), mechanic.getLength(), mechanic.getWitdh(), mechanic.getHeight())) {

            if (worldGuardCompatibility != null && !worldGuardCompatibility.canBreak(player, block))
                return;

            FertilizerMechanicManager.fertilized(block, 5);
        }
    }

    public static List<Block> getNearbyBlocks(Location location, int l, int L, int H) {
        List<Block> blocks = new ArrayList<>();
        for (int x = location.getBlockX() - Math.floorDiv(l, 2); x <= location.getBlockX() + Math.floorDiv(l, 2); x++)
            for (int y = location.getBlockY() - Math.floorDiv(H, 2); y <= location.getBlockY() + Math.floorDiv(H, 2); y++)
                for (int z = location.getBlockZ() - Math.floorDiv(L, 2); z <= location.getBlockZ() + Math.floorDiv(L, 2); z++)
                    blocks.add(Objects.requireNonNull(location.getWorld()).getBlockAt(x, y, z));

        return blocks;
    }

    public static void fertilized(Block block, int count) {
        for(int i = 0; i < count; i++)
            block.applyBoneMeal(BlockFace.UP);
    }
}
