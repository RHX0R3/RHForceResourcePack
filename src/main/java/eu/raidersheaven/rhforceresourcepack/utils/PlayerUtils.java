package eu.raidersheaven.rhforceresourcepack.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerUtils {

    static Config cfg = new Config();


    public static boolean havePermission(Player player, String permission) {

        return player.hasPermission("rhfrp.*") || player.hasPermission(permission);

    }


    public static void soundAccept(Player player) {

        if (cfg.soundsEnabled) {

            player.playSound(player.getLocation(), Sound.valueOf(cfg.acceptSound), 1.0f, 0.6f);

        }

    }


    public static void soundDeny(Player player) {

        if (cfg.soundsEnabled) {

            player.playSound(player.getLocation(), Sound.valueOf(cfg.denySound), 1.0f, 0.6f);

        }

    }


    public static void soundSuccess(Player player) {

        if (cfg.soundsEnabled) {

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5f, 0.6f);

        }

    }


    public static void soundFail(Player player) {

        if (cfg.soundsEnabled) {

            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5f, 0.6f);
            player.playSound(player.getLocation(), Sound.BLOCK_LAVA_POP, 0.5f, 1.0f);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 0.5f, 0.3f);

        }

    }

}