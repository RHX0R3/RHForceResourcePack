package eu.raidersheaven.rhforceresourcepack.main;

import eu.raidersheaven.rhforceresourcepack.utils.Config;
import eu.raidersheaven.rhforceresourcepack.utils.HexColor;
import eu.raidersheaven.rhforceresourcepack.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

import java.util.Objects;

public class ResourcepackEvents implements Listener {

    static Config cfg = new Config();

    final String packURL = cfg.packURL;
    final int promptDelay = cfg.promptDelay;
    final boolean kickEnabled = cfg.kickEnabled;
    final int kickDelay = cfg.kickDelay;
    final boolean skipEnabled = cfg.skipEnabled;
    final String prefix = cfg.prefix;
    final String playerKicked = cfg.playerKicked;
    final String packAccepted = cfg.packAccepted;
    final String packDenied = cfg.packDenied;
    final String packSkipped = cfg.packSkipped;
    final boolean packLoadedEnabled = cfg.packLoadedEnabled;
    final String packLoaded = cfg.packLoaded;
    final int soundsDelay = cfg.soundsDelay;


    @EventHandler
    public void onjoin(final PlayerJoinEvent e) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {

            final String source = packURL;
            final Player joinPlayer = e.getPlayer();

            if (source != null && !source.equalsIgnoreCase("")) {
                joinPlayer.setResourcePack(source);
            }

        }, (promptDelay <= 0) ? 1 : promptDelay);

    }

    @EventHandler
    public void resourcePackStatus(final PlayerResourcePackStatusEvent e) {

        final Player p = e.getPlayer();
        FileConfiguration configuration = Main.getInstance().getConfig();
        String consoleKicked = Objects.requireNonNull(HexColor.format(configuration.getString("messages.console-kick"))).replace("%player%", p.getName()).replace("%prefix%", prefix);
        String consoleSkipped = Objects.requireNonNull(HexColor.format(configuration.getString("messages.console-skip"))).replace("%player%", p.getName()).replace("%prefix%", prefix);
        String consoleAccepted = Objects.requireNonNull(HexColor.format(configuration.getString("messages.console-accept"))).replace("%player%", p.getName()).replace("%prefix%", prefix);
        String consoleDenied = Objects.requireNonNull(HexColor.format(configuration.getString("messages.console-deny"))).replace("%player%", p.getName()).replace("%prefix%", prefix);
        String consoleFail = Objects.requireNonNull(HexColor.format(configuration.getString("messages.console-fail"))).replace("%player%", p.getName()).replace("%prefix%", prefix);


        if (e.getStatus() == PlayerResourcePackStatusEvent.Status.ACCEPTED) {

            Bukkit.getConsoleSender().sendMessage(consoleAccepted);
            p.sendMessage(packAccepted);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

                PlayerUtils.soundAccept(p);

            }, (soundsDelay <= 0) ? 1 : soundsDelay);

        } else if (e.getStatus() == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD) {

            Bukkit.getConsoleSender().sendMessage(consoleFail);

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

                PlayerUtils.soundDeny(p);

            }, (soundsDelay <= 0) ? 1 : soundsDelay);

        } else if (e.getStatus() == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {

            if (packLoadedEnabled) {

                p.sendMessage(packLoaded);

            }

        } else if (e.getStatus() == PlayerResourcePackStatusEvent.Status.DECLINED) {

            if (kickEnabled && skipEnabled && PlayerUtils.havePermission(p, "RHFRP.skip")) {

                Bukkit.getConsoleSender().sendMessage(consoleSkipped);
                p.sendMessage(packSkipped);
                PlayerUtils.soundAccept(p);

            } else if (kickEnabled && skipEnabled && (!PlayerUtils.havePermission(p, "RHFRP.skip"))) {

                Bukkit.getConsoleSender().sendMessage(consoleDenied);
                p.sendMessage(packDenied);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

                    PlayerUtils.soundDeny(p);

                }, (soundsDelay <= 0) ? 1 : soundsDelay);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

                    p.kickPlayer(ChatColor.translateAlternateColorCodes('&', playerKicked));
                    Bukkit.getConsoleSender().sendMessage(consoleKicked);

                }, 20L * kickDelay);

            } else if (kickEnabled && !skipEnabled) {

                Bukkit.getConsoleSender().sendMessage(consoleDenied);
                p.sendMessage(packDenied);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

                    PlayerUtils.soundDeny(p);

                }, (soundsDelay <= 0) ? 1 : soundsDelay);

                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, () -> {

                    p.kickPlayer(ChatColor.translateAlternateColorCodes('&', playerKicked));
                    Bukkit.getConsoleSender().sendMessage(consoleKicked);

                }, 20L * kickDelay);

            } else if (!kickEnabled && !skipEnabled) {

                // ignore pack denial and do nothing

            }

        }

    }

}