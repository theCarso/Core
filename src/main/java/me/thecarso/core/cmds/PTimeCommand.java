package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PTimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.ptime")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            Lang.PTIME_GET.send(sender, player.getWorld().getTime() + "", player.getPlayerTime()%24000 + "");
            Lang.INVALID_ARGS_NOPREFIX.send(sender, "/" + label + " <day;noon;night;# or reset>");
            return true;
        }
        if (args[0].equalsIgnoreCase("reset")) {
            player.resetPlayerTime();
            Lang.PTIME_RESET.send(sender);
            return true;
        }
        if (args[0].equalsIgnoreCase("day")) {
            player.setPlayerTime(1000, false);
            Lang.PTIME_SET.send(sender, "day (1000)");
            return true;
        }
        if (args[0].equalsIgnoreCase("noon")) {
            player.setPlayerTime(6000, false);
            Lang.PTIME_SET.send(sender, "noon (6000)");
            return true;
        }
        if (args[0].equalsIgnoreCase("night")) {
            player.setPlayerTime(13000, false);
            Lang.PTIME_SET.send(sender, "night (13000)");
            return true;
        }
        int time;
        try {
            time = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            Lang.INVALID_ARGS_NOPREFIX.send(sender, "/" + label + " <day;noon;night;# or reset>");
            return true;
        }
        player.setPlayerTime(time, false);
        Lang.PTIME_SET.send(sender, time + "");

        return true;
    }
}
