package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.WeatherType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PWeatherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.pweather")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            Lang.PWEATHER_GET.send(sender, player.getPlayerWeather() + "");
            Lang.INVALID_ARGS_NOPREFIX.send(sender, "/" + label + " <clear;rain or reset>");
            return true;
        }
        if (args[0].equalsIgnoreCase("reset")) {
            player.resetPlayerWeather();
            Lang.PWEATHER_RESET.send(sender);
            return true;
        }
        if (args[0].equalsIgnoreCase("clear")) {
            player.setPlayerWeather(WeatherType.CLEAR);
            Lang.PWEATHER_SET.send(sender, "clear");
            return true;
        }
        if (args[0].equalsIgnoreCase("rain")) {
            player.setPlayerWeather(WeatherType.DOWNFALL);
            Lang.PWEATHER_SET.send(sender, "rain");
            return true;
        }
        Lang.INVALID_ARGS_NOPREFIX.send(sender, "/" + label + " <clear;rain or reset>");

        return true;
    }
}
