package io.github.marblokfromminecraft.datapackTools;

import io.github.marblokfromminecraft.datapackTools.commands.Pathfind;
import io.github.marblokfromminecraft.datapackTools.util.LoggerSettings;
import org.bukkit.plugin.java.JavaPlugin;

public final class DatapackTools extends JavaPlugin {
    @Override
    public void onEnable() {
        //todo: switch commands to brigadier api (bukkitcommand does not work in datapack)

        // Plugin startup logic
        getCommand("pathfind").setExecutor(new Pathfind());

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
