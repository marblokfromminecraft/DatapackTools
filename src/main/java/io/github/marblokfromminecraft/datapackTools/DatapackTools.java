package io.github.marblokfromminecraft.datapackTools;

import io.github.marblokfromminecraft.datapackTools.commands.Pathfind;
import org.bukkit.plugin.java.JavaPlugin;

public final class DatapackTools extends JavaPlugin {
//todo: add setting to startup and test pathfind command, previous bug: /pathfind no argument = stacktrace;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("pathfind").setExecutor(new Pathfind());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static DatapackTools getInstance(){
        return getPlugin(DatapackTools.class);
    }
}
