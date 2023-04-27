package eu.raidersheaven.rhforceresourcepack.managers;

import eu.raidersheaven.rhforceresourcepack.main.Main;
import eu.raidersheaven.rhforceresourcepack.commands.ReloadCommand;
import eu.raidersheaven.rhforceresourcepack.commands.ResendCommand;

import java.util.Objects;

public class CommandManager {

    public CommandManager() {

        Objects.requireNonNull(Main.getInstance().getCommand("resourcepack")).setExecutor(new ReloadCommand());
        Objects.requireNonNull(Main.getInstance().getCommand("resourcepack")).setExecutor(new ResendCommand());

    }

}
