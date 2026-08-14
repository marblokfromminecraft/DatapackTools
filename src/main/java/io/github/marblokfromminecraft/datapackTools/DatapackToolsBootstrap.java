package io.github.marblokfromminecraft.datapackTools;

import io.github.marblokfromminecraft.datapackTools.commands.Pathfind;
import io.github.marblokfromminecraft.datapackTools.commands.RegisterCommand;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class DatapackToolsBootstrap implements PluginBootstrap {
    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        context.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, command -> {
            Pathfind.register(command.registrar());
            RegisterCommand.register(command.registrar());
        });

    }
}
