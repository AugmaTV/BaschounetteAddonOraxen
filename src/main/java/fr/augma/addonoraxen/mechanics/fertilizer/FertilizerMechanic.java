package fr.augma.addonoraxen.mechanics.fertilizer;

import fr.augma.addonoraxen.mechanics.IAddonMechanic;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import org.bukkit.configuration.ConfigurationSection;

public class FertilizerMechanic extends Mechanic implements IAddonMechanic {

    private final int length;
    private final int width;
    private final int height;
    private final int cdInSec;
    private final String timeLeftSentence;

    public FertilizerMechanic(MechanicFactory mechanicFactory, ConfigurationSection section) {
        super(mechanicFactory, section);
        this.length = section.getInt("length");
        this.width = section.getInt("width");
        this.height = section.getInt("height");
        this.cdInSec = section.getInt("cooldownInSec");
        this.timeLeftSentence = section.getString("timeLeftSentence");
    }

    public int getLength() {
        return this.length;
    }

    public int getWitdh() {
        return this.width;
    }

    public int getHeight() {
        return  this.height;
    }

    public int getCooldownInSec() {
        return this.cdInSec;
    }

    public String getTimeLeftSentence() {
        return this.timeLeftSentence.replace('&', '§');
    }
}
