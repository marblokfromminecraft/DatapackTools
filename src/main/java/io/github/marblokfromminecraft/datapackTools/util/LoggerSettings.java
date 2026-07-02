package io.github.marblokfromminecraft.datapackTools.util;

import io.github.marblokfromminecraft.datapackTools.DatapackTools;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LoggerSettings {
    private final static LoggerSettings Instance = new LoggerSettings();

    private File file;
    private YamlConfiguration config;

    public String loggerMode;


    private LoggerSettings(){
    }
    public void load() {
        file = new File(DatapackTools.getInstance().getDataFolder(), "settings.yml");

        if (!file.exists()) {
            DatapackTools.getInstance().saveResource("settings.yml", false);
        }

        config = new YamlConfiguration();
        config.options().parseComments(true);

        try {
            config.load(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        loggerMode = config.getString("Logsettings.LOG_MODE");
        DatapackTools.getInstance().getLogger().info("Log mode currently set to " + loggerMode + "you can change this in the DatapackTools folder in your plugins folder");

    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String get(){
        return loggerMode;
    }


    public static LoggerSettings getInstance() {
        return Instance;
    }
}
