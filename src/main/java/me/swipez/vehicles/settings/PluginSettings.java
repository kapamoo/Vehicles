package me.swipez.vehicles.settings;

import me.swipez.vehicles.VehiclesPlugin;

public class PluginSettings {

    public boolean ownerOnlyRider = false;
    public boolean planes = true;

    public PluginSettings(){
        ownerOnlyRider = VehiclesPlugin.mainConfig.getConfig().getBoolean("owner-only-drivers");
        planes = VehiclesPlugin.mainConfig.getConfig().getBoolean("planes");
    }
}
