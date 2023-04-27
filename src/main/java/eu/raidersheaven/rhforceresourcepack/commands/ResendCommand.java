package eu.raidersheaven.rhforceresourcepack.commands;

import eu.raidersheaven.rhforceresourcepack.main.Main;
import eu.raidersheaven.rhforceresourcepack.utils.Config;
import eu.raidersheaven.rhforceresourcepack.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ResendCommand implements CommandExecutor, TabExecutor {

    public ResendCommand() {

        Objects.requireNonNull(Main.getInstance().getCommand("resourcepack")).setTabCompleter(this);

    }

    static Config cfg = new Config();

    final String packURL = cfg.packURL;
    final int promptDelay = cfg.promptDelay;
    final String packResent = cfg.packResent;
    final String noPermission = cfg.noPermission;
    final String consoleNoPlayer = cfg.consoleNoPlayer;
    final String wrongArgument = cfg.wrongArgument;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            if (args.length > 0) {

                if (args[0].equalsIgnoreCase("resend")) {

                    if (PlayerUtils.havePermission(p, "rhfrp.resend")) {

                        resend(p);

                    } else {

                        p.sendMessage(noPermission);
                        PlayerUtils.soundFail(p);

                    }

                } else if (args[0].equalsIgnoreCase("resendAll")) {

                    if (PlayerUtils.havePermission(p, "rhfrp.resendall")) {

                        resendAll(Bukkit.getOnlinePlayers());

                    } else {

                        p.sendMessage(noPermission);
                        PlayerUtils.soundFail(p);

                    }

                }

            } else {

                p.sendMessage(wrongArgument);
                PlayerUtils.soundSuccess(p);

            }

            return true;
        }

        sender.sendMessage(consoleNoPlayer);
        return false;

    }


    private void resend(Player player) {

        Main.getInstance().reloadConfig();
        final int delay = promptDelay;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

            final String source = packURL;
            if (source != null && !source.equalsIgnoreCase("")) {
                player.setResourcePack(source);
            }

        }, (delay <= 0) ? 1 : delay);

        player.sendMessage(packResent);
        PlayerUtils.soundSuccess(player);

    }


    private void resendAll(Collection<? extends Player> onlinePlayers) {

        Main.getInstance().reloadConfig();
        final int delay = promptDelay;
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

            final String source = packURL;

            if (source != null && !source.equalsIgnoreCase("")) {

                for (Player player : Bukkit.getOnlinePlayers()) {

                    player.setResourcePack(source);

                }

            }

        }, (delay <= 0) ? 1 : delay);

        for (Player p : Bukkit.getOnlinePlayers()) {

            p.sendMessage(packResent);
            PlayerUtils.soundSuccess(p);

        }

    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        final List<String> l = new ArrayList<>();
        final Player player = (Player) sender;

        if (args.length == 1) {

            if (PlayerUtils.havePermission(player, "RHFRP.resend")) {

                l.add("resend");

            }

            if (PlayerUtils.havePermission(player, "RHFRP.resendall")) {

                l.add("resendall");

            }

        }

        return l;
    }

}