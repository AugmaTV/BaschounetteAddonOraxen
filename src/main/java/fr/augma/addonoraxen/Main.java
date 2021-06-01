package fr.augma.addonoraxen;

import fr.augma.addonoraxen.mechanics.cooldown.CooldownManager;
import fr.augma.addonoraxen.mechanics.fertilizer.FertilizerMechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private CooldownManager FertilizerManager;
    private CooldownManager PotionSplashManager;

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
