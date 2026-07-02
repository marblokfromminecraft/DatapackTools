package io.github.marblokfromminecraft.datapackTools.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.github.marblokfromminecraft.datapackTools.util.Logger;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.EntitySelectorArgumentResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.FinePositionResolver;
import io.papermc.paper.math.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;

import java.util.List;

public class Pathfind {

    public static void register(Commands dispatcher) {
        dispatcher.register(
            Commands.literal("pathfind")
                .then(Commands.argument("entities", ArgumentTypes.entities())
                    .then(Commands.argument("position", ArgumentTypes.finePosition(true))
                        .executes(Pathfind::execute)
                    )
                )
                .build(),
            "a command to make entities pathfind to a specific location"
        );
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        EntitySelectorArgumentResolver entitiesResolver = ctx.getArgument("entities", EntitySelectorArgumentResolver.class);
        List<Entity> entities = entitiesResolver.resolve(ctx.getSource());

        FinePositionResolver posResolver = ctx.getArgument("position", FinePositionResolver.class);
        Position pos = posResolver.resolve(ctx.getSource());

        Location sourceLoc = ctx.getSource().getLocation();
        World world = sourceLoc != null ? sourceLoc.getWorld() : null;
        
        if (world == null) {
            Logger.warning("could not determine world for pathfind command");
            return Command.SINGLE_SUCCESS;
        }

        Location target = new Location(world, pos.x(), pos.y(), pos.z());

        if (entities.isEmpty()) {
            Logger.info("no entities where found");
            return Command.SINGLE_SUCCESS;
        }

        for (Entity entity : entities) {
            if (entity instanceof Mob) {
                Mob mob = (Mob) entity;
                Bukkit.getMobGoals().removeAllGoals(mob);
                mob.getPathfinder().moveTo(target);
            } else {
                Logger.info(entity.getName() + " is not a mob, skipping");
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}
