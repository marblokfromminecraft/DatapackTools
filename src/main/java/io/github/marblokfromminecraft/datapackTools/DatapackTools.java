package io.github.marblokfromminecraft.datapackTools;

import io.github.marblokfromminecraft.datapackTools.util.LoggerSettings;
import org.bukkit.plugin.java.JavaPlugin;

public final class DatapackTools extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic

        LoggerSettings.getInstance().load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static DatapackTools getInstance(){
        return getPlugin(DatapackTools.class);
    }
}
