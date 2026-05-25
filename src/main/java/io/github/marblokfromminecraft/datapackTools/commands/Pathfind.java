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
            if (strings.length < 1) {
                Logger.info("you cant use /pathfind without arguments");
                return false;
            }
            if (!(strings.length > 3 || allInts(strings))) {
                Logger.info("strings is not bigger than 3 or not all arguments are doubles");
                return false;
            }
            //getting the entity
            String selector = "";
            java.util.List<org.bukkit.entity.Entity> entities;
            for (int i = 3; i < strings.length; i++) {
                selector = selector + strings[i];
            }
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
                    Location target = new Location(mob.getWorld(), Double.parseDouble(strings[0]), Double.parseDouble(strings[1]), Double.parseDouble(strings[2]));
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

        private boolean allInts (String [] array) {

        for (int i = 0; i<3; i++) {
            try {
                Double.parseDouble(array[i]);
            } catch (NumberFormatException nfe) {
                DatapackTools.getInstance().getServer().getLogger().info("argument" + i + "is not a double");
                return false;
            }
        }

        return true;
    }

}
