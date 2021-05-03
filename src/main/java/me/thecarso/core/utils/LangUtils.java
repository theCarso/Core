package me.thecarso.core.utils;

import me.thecarso.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LangUtils {

    static {
        PREFIX = Core.getConfigFile().getFile().getString("prefix");
    }

    private static String PREFIX;

    public static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void sendMessage(CommandSender player, String message) {
        for (String string : message.split("<newline>")) {
            player.sendMessage(colorize(string));
        }
    }

    public static void sendPrefixedMessage(CommandSender player, String message) {
        sendMessage(player, PREFIX + message);
    }

    public static void broadcastMessage(String string) {
        Bukkit.broadcastMessage(colorize(string));
    }

    public static void broadcastPrefixedMessage(String string) {
        Bukkit.broadcastMessage(colorize(PREFIX + string));
    }


    public static String getTimeString(long l) {
        long seconds = (l - System.currentTimeMillis()) / 1000;

        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("d ");
        }
        if (hours > 0) {
            sb.append(hours).append("h ");
        }
        if (minute > 0) {
            sb.append(minute).append("m ");
        }
        if (second > 0) {
            sb.append(second).append("s ");
        }
        return sb.toString().trim();
    }
    public static String getSeenString(long l) {
        long seconds = (System.currentTimeMillis() - l) / 1000;

        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("d ");
        }
        if (hours > 0) {
            sb.append(hours).append("h ");
        }
        if (minute > 0) {
            sb.append(minute).append("m ");
        }
        if (second > 0) {
            sb.append(second).append("s ");
        }
        return sb.toString().trim();
    }
}
