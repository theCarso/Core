package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.eat")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                Lang.CONSOLE_SENDER_ERROR.send(sender);
                return true;
            }
            Player player = (Player) sender;
            player.setFoodLevel(20);
            player.setSaturation(10);
            Lang.EAT_SUCCESS.send(sender);
            return true;
        }
        if (args.length == 1) {
            if (!sender.hasPermission("core.eat.others")) {
                Lang.PERMISSION_DENIED.send(sender);
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                target.setFoodLevel(20);
                target.setSaturation(10);
                Lang.EAT_SUCCESS.send(target);
                Lang.EAT_SUCCESS_OTHER.send(sender, target.getName());
            } else {
                Lang.EAT_INVALID_PLAYER.send(sender, args[0]);
            }
            return true;
        }
        Lang.INVALID_ARGS.send(sender, "/" + label + " [player]");
        return true;
    }
}
