package io.github.marblokfromminecraft.datapackTools.commands;


import io.github.marblokfromminecraft.datapackTools.DatapackTools;
import io.github.marblokfromminecraft.datapackTools.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;


public class Pathfind implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull org.bukkit.command.CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < 4) {
            Logger.info("you cant use /pathfind without enough arguments");
            return false;
        }

        double x, y, z;
        try {
            x = Double.parseDouble(strings[0]);
            y = Double.parseDouble(strings[1]);
            z = Double.parseDouble(strings[2]);
        } catch (NumberFormatException e) {
            Logger.info("arguments are not valid coordinates");
            return false;
        }

        //getting the entity
        String selector = String.join(" ", java.util.Arrays.copyOfRange(strings, 3, strings.length));
        java.util.List<org.bukkit.entity.Entity> entities;
        
        try {
            entities = DatapackTools.getInstance().getServer().selectEntities(commandSender, selector);
        } catch (IllegalArgumentException e) {
            commandSender.sendMessage("invalid entity selector");
            Logger.warning(selector + " is an invalid entity selector");
            return true;
        }

        if (entities.isEmpty()) {
            commandSender.sendMessage("no entities matching " + selector + " where found");
            Logger.info("no entities with selector " + selector + " where found");
            return true;
        }

        for (Entity entity : entities) {
            if (entity instanceof Mob) {
                Mob mob = (Mob) entity;
                Location target = new Location(mob.getWorld(), x, y, z);
                Bukkit.getMobGoals().removeAllGoals(mob);
                mob.getPathfinder().moveTo(target);
            } else {
                Logger.info(entity.getName() + " is not a mob, skipping");
            }
        }
        return true;
    }

    @Override
    public java.util.List<String> onTabComplete(@NotNull org.bukkit.command.CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        return java.util.List.of("");
    }
}
