package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.clear")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                Lang.CONSOLE_SENDER_ERROR.send(sender);
                return true;
            }
            Player player = (Player) sender;
            player.getInventory().clear();
            Lang.CLEAR_SUCCESS.send(sender);
            return true;
        }

        if (args.length == 1) {
            if (!sender.hasPermission("core.clear.other")) {
                Lang.PERMISSION_DENIED.send(sender);
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                Lang.CLEAR_INVALID_PLAYER.send(sender, args[0]);
                return true;
            }
            target.getInventory().clear();
            Lang.CLEAR_OTHER_SUCCESS.send(sender, target.getName());
            Lang.CLEAR_SUCCESS.send(target, sender.getName());
            return true;
        }
        Lang.INVALID_ARGS.send(sender, "/" + label + " [player]");

        return true;
    }
}
