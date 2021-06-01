package fr.augma.addonoraxen;

import fr.augma.addonoraxen.mechanics.cooldown.CooldownManager;
import fr.augma.addonoraxen.mechanics.fertilizer.FertilizerMechanic;
import fr.augma.addonoraxen.mechanics.fertilizer.FertilizerMechanicFactory;
import fr.augma.addonoraxen.mechanics.potionsplash.PotionSplashMechanic;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private CooldownManager<FertilizerMechanic> FertilizerManager;
    private CooldownManager<PotionSplashMechanic> PotionSplashManager;

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.FertilizerManager = new CooldownManager();
        this.PotionSplashManager = new CooldownManager();
        MechanicsManager.registerMechanicFactory("fertilizer", FertilizerMechanicFactory.class);
    }

    public static Main get() {
        return plugin;
    }

    public CooldownManager getFertilizerManager() {
        return this.FertilizerManager;
    }

    public CooldownManager getPotionSplashManager() {
        return this.PotionSplashManager;
    }
}
