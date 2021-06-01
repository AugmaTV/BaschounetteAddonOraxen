package fr.augma.addonoraxen.mechanics.potionsplash;

import fr.augma.addonoraxen.mechanics.IAddonMechanic;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class PotionSplashMechanic extends Mechanic implements IAddonMechanic {

    private final PotionEffectType potionType;
    private final int cooldownInSec;
    private final String timeLeftSentence;

    public PotionSplashMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {
        super(mechanicFactory, section);
        this.potionType = PotionEffectType.getByName(Objects.requireNonNull(section.getString("potionEffect")));
        this.cooldownInSec = section.getInt("cooldownInSec");
        this.timeLeftSentence = section.getString("timeLeftSentence");
    }

    public PotionEffectType getPotionType() {
        return this.potionType;
    }

    @Override
    public int getCooldownInSec() {
        return this.cooldownInSec;
    }

    @Override
    public String getTimeLeftSentence() {
        return this.timeLeftSentence.replace('&', '§');
    }
}
