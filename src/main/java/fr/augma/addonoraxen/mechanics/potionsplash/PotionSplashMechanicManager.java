package fr.augma.addonoraxen.mechanics.potionsplash;

import fr.augma.addonoraxen.Main;
import io.th0rgal.oraxen.items.OraxenItems;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class PotionSplashMechanicManager implements Listener {

    private final MechanicFactory factory;

    public PotionSplashMechanicManager (MechanicFactory factory) {
        this.factory = factory;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getClickedBlock() == null)
            return;

        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        if (item == null)
            return;

        String itemID = OraxenItems.getIdByItem(item);

        if (factory.isNotImplementedIn(itemID))
            return;

        PotionSplashMechanic mechanic = (PotionSplashMechanic) factory.getMechanic(itemID);

        Player player = e.getPlayer();

        if(!Main.get().getPotionSplashManager().playerCanUse(player, mechanic))
            return;

        this.applyEffectOnWorld(mechanic.getPotionType(), player.getWorld(), e.getClickedBlock().getLocation());
        System.out.println("jsp encore bro fait pas chier sérieux nan mais oh t'as crue j'etait ta pute ?");
    }

    private void applyEffectOnWorld(PotionEffectType effect, World world, Location location) {
        //world.playEffect(location, effect, );
    }
}
