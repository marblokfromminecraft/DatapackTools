package io.github.marblokfromminecraft.datapackTools.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.github.marblokfromminecraft.datapackTools.util.Logger;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RegisterCommand {
    public static void register(io.papermc.paper.command.brigadier.Commands dispatcher) {
        dispatcher.register(
                Commands.literal("registerCommand")
                        .then(Commands.argument("name", StringArgumentType.word())
                                .then(Commands.argument("function", ArgumentTypes.namespacedKey())
                                .executes(RegisterCommand::execute)
                                )

                        )
                        .build()
        );
    }

    private static int execute (CommandContext<CommandSourceStack> ctx) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        String name = StringArgumentType.getString(ctx, "name");
        NamespacedKey functionKey = ctx.getArgument("function", NamespacedKey.class);

        org.bukkit.command.Command newCommand = new org.bukkit.command.Command(name) {
            @Override
            public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
                Bukkit.getServer().dispatchCommand(sender, "function " + functionKey.toString());
                return true;
            }
        };

        Bukkit.getServer().getCommandMap().register("datapack", newCommand);
        Bukkit.getOnlinePlayers().forEach(org.bukkit.entity.Player::updateCommands);

        Logger.info("Successfully registered command " + name);

        return Command.SINGLE_SUCCESS;
    }
}
