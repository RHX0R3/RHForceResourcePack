package eu.raidersheaven.rhforceresourcepack.commands;

import eu.raidersheaven.rhforceresourcepack.main.Main;
import eu.raidersheaven.rhforceresourcepack.utils.Config;
import eu.raidersheaven.rhforceresourcepack.utils.PlayerUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReloadCommand implements CommandExecutor, TabExecutor {

    public ReloadCommand() {

        Objects.requireNonNull(Main.getInstance().getCommand("resourcepack")).setTabCompleter(this);
    }

    static Config cfg = new Config();

    final String reload = cfg.reload;
    final String noPermission = cfg.noPermission;
    final String consoleNoPlayer = cfg.consoleNoPlayer;
    final String wrongArgument = cfg.wrongArgument;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 0) {

                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rel")) {

                    if (PlayerUtils.havePermission(p, "rhfrp.reload")) {

                        reload(p);

                    } else {

                        p.sendMessage(noPermission);
                        PlayerUtils.soundFail(p);

                    }

                }

            } else {

                p.sendMessage(wrongArgument);
                PlayerUtils.soundFail(p);

            }

            return true;
        }

        sender.sendMessage(consoleNoPlayer);
        return false;

    }


    private void reload(Player player) {

        Main.getInstance().reloadConfig();
        player.sendMessage(reload);
        PlayerUtils.soundSuccess(player);

    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        final List<String> l = new ArrayList<>();
        final Player player = (Player) sender;

        if (args.length == 1) {

            if (PlayerUtils.havePermission(player, "RHFRP.reload")) {

                l.add("reload");

            }

        }

        return l;
    }

}
