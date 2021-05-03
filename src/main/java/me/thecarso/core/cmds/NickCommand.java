package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (!sender.hasPermission("core.nick")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length > 2 || args.length == 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <name> OR /" + label + " <player> <name>");
            return true;
        }

        Player player = (Player) sender;
        User user = Core.getUserManager().getUser(player);
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("off")) {
                player.setDisplayName(player.getName());
                user.setNick(player.getName());
                Lang.NICK_RESET_PLAYER.send(sender);
                return true;
            }
            if (args[0].length() > 25) {
                Lang.NICK_LIMIT.send(sender);
                return true;
            }
            player.setDisplayName(LangUtils.colorize(args[0]));
            user.setNick(LangUtils.colorize(args[0]));
            Lang.NICK_SET_PLAYER.send(sender, LangUtils.colorize(args[0]));
            return true;
        }

        if (!sender.hasPermission("core.nick.other")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            Lang.NICK_INVALID_PLAYER.send(sender, args[0]);
            return true;
        }
        User targetUser = Core.getUserManager().getUser(target);
        if (args[1].equalsIgnoreCase("reset") || args[1].equalsIgnoreCase("off")) {
            target.setDisplayName(target.getName());
            targetUser.setNick(target.getName());
            Lang.NICK_RESET_OTHER.send(sender, target.getName());
            return true;
        }
        if (args[1].length() > 25) {
            Lang.NICK_LIMIT.send(sender);
            return true;
        }

        target.setDisplayName(LangUtils.colorize(args[1]));
        targetUser.setNick(LangUtils.colorize(args[1]));

        Lang.NICK_SET_PLAYER.send(target, LangUtils.colorize(args[1]));
        Lang.NICK_SET_OTHER.send(sender, target.getName(), LangUtils.colorize(args[1]));
        return true;
    }
}
