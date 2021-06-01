package fr.augma.addonoraxen.mechanics.fertilizer;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.mechanics.Mechanic;
import io.th0rgal.oraxen.mechanics.MechanicFactory;
import io.th0rgal.oraxen.mechanics.MechanicsManager;
import org.bukkit.configuration.ConfigurationSection;

public class FertilizerMechanicFactory extends MechanicFactory {

    public FertilizerMechanicFactory(ConfigurationSection section) {
        super(section);
        MechanicsManager.registerListeners(OraxenPlugin.get(), new FertilizerMechanicManager(this));
    }

    @Override
    public Mechanic parse(ConfigurationSection itemMechanicConfiguration) {
        Mechanic mechanic = new FertilizerMechanic(this, itemMechanicConfiguration);
        addToImplemented(mechanic);
        return mechanic;
    }
}
