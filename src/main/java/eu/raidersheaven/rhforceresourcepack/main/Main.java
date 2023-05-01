package eu.raidersheaven.rhforceresourcepack.main;

import eu.raidersheaven.metrics.Metrics;
import eu.raidersheaven.rhforceresourcepack.managers.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    public static String name = (ChatColor.DARK_GRAY + "[" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "RHForceResourcePack" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + " ");
    public static String version = "1.19_R3";


    @Override
    public void onEnable() {

        plugin = this;

        int pluginId = 11910;
        Metrics metrics = new Metrics(this, pluginId);

        saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new ResourcepackEvents(), this);

        new CommandManager();

        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GRAY + "The plugin has been " + ChatColor.GREEN + ChatColor.BOLD + "loaded" + ChatColor.GRAY + "!");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GRAY + "You are using version " + ChatColor.GREEN + version + ChatColor.GRAY + " /w " + ChatColor.RED + "💕 " + ChatColor.GRAY + "by " + ChatColor.AQUA + "X0R3");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.DARK_GRAY + "Info:");
        Bukkit.getConsoleSender().sendMessage(name + ChatColor.DARK_GRAY + "If you want your server be presented on the plugin page, then please contact me! :)");

    }

    
    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(name + ChatColor.GRAY + "The plugin has been " + ChatColor.RED + ChatColor.BOLD + "unloaded" + ChatColor.GRAY + "!");

    }

    
    public static Main getInstance() {

        return plugin;

    }

}
