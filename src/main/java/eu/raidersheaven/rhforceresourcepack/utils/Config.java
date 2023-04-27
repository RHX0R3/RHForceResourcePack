package eu.raidersheaven.rhforceresourcepack.utils;

import eu.raidersheaven.rhforceresourcepack.main.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class Config {

    public FileConfiguration configuration = Main.getInstance().getConfig();
    public String packURL;
    public int promptDelay;
    public boolean kickEnabled;
    public int kickDelay;
    public boolean skipEnabled;
    public String prefix;
    public String playerKicked;
    public String packAccepted;
    public String packDenied;
    public String packResent;
    public String packSkipped;
    public String reload;
    public String wrongArgument;
    public String noPermission;
    public String consoleNoPlayer;
    public boolean packLoadedEnabled;
    public String packLoaded;
    public boolean soundsEnabled;
    public String acceptSound;
    public String denySound;
    public int soundsDelay;


    public Config() {

        packURL = Objects.requireNonNull(configuration.getString("resourcepack-url"));
        promptDelay = configuration.getInt("prompt.delay");
        kickEnabled = configuration.getBoolean("kick.enabled");
        kickDelay = configuration.getInt("kick.delay");
        skipEnabled = configuration.getBoolean("skip-permission");
        prefix = HexColor.format(configuration.getString("messages.prefix"));
        playerKicked = HexColor.format(Objects.requireNonNull(configuration.getString("messages.player-kicked")).replaceAll("%prefix%", prefix));
        packAccepted = HexColor.format(configuration.getString("messages.pack-accepted")).replace("%prefix%", prefix);
        packDenied = HexColor.format(configuration.getString("messages.pack-denied")).replace("%prefix%", prefix);
        packResent = HexColor.format(configuration.getString("messages.pack-resent")).replace("%prefix%", prefix);
        packSkipped = HexColor.format(configuration.getString("messages.pack-skipped")).replace("%prefix%", prefix);
        reload = HexColor.format(configuration.getString("messages.reload")).replace("%prefix%", prefix);
        wrongArgument = HexColor.format(configuration.getString("messages.wrong-argument")).replace("%prefix%", prefix);
        noPermission = HexColor.format(configuration.getString("messages.no-permission")).replace("%prefix%", prefix);
        consoleNoPlayer = HexColor.format(configuration.getString("messages.console-no-player")).replace("%prefix%", prefix);
        packLoadedEnabled = configuration.getBoolean("messages.optional.pack-loaded.enabled");
        packLoaded = HexColor.format(configuration.getString("messages.optional.pack-loaded.message")).replace("%prefix%", prefix);
        soundsEnabled = configuration.getBoolean("sounds.enabled");
        acceptSound = Objects.requireNonNull(configuration.getString("sounds.accept-sound")).toUpperCase();
        denySound = Objects.requireNonNull(configuration.getString("sounds.deny-sound")).toUpperCase();
        soundsDelay = configuration.getInt("sounds.delay");

    }

}
