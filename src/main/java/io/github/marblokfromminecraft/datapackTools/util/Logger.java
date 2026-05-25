package io.github.marblokfromminecraft.datapackTools.util;

import io.github.marblokfromminecraft.datapackTools.DatapackTools;

public class Logger {
    public static void info (Object message) {
        if (LoggerSettings.getInstance().get().equals("ALL")) {
            DatapackTools.getInstance().getServer().getLogger().info(message.toString());
        }
    }
    public static void warning (Object message) {
        if (LoggerSettings.getInstance().get().equals("ALL") || LoggerSettings.getInstance().get().equals("IMPORTANT")) {
            DatapackTools.getInstance().getServer().getLogger().warning(message.toString());
        }
    }
}
