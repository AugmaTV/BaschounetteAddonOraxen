package fr.augma.addonoraxen.mechanics.cooldown;

import fr.augma.addonoraxen.mechanics.IAddonMechanic;
import fr.augma.addonoraxen.mechanics.fertilizer.FertilizerMechanic;
import io.th0rgal.oraxen.mechanics.Mechanic;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager {

    private final HashMap<UUID, Long> uuidLongHashMap;

    public CooldownManager() {
        this.uuidLongHashMap = new HashMap<>();
    }

    public void addPlayer(Player player) {
        this.uuidLongHashMap.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public <T extends IAddonMechanic> boolean playerCanUse(Player player, T  mechanic) {

        if(!this.uuidLongHashMap.containsKey(player.getUniqueId())) {
            this.addPlayer(player);
            return true;
        }

        if(System.currentTimeMillis() - this.uuidLongHashMap.get(player.getUniqueId()) >= (mechanic.getCooldownInSec() * 1000L)) {
            this.uuidLongHashMap.remove(player.getUniqueId());
            return true;
        }

        long time_left = (mechanic.getCooldownInSec() * 1000L) - (System.currentTimeMillis() - this.uuidLongHashMap.get(player.getUniqueId()));
        time_left /= 1000;

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(mechanic.getTimeLeftSentence().replace("%TIME_LEFT%", String.valueOf(time_left))));

        return false;
    }
}
