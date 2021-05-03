package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EChestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.echest")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
            return true;
        }

        if (args.length == 1) {
            if (!sender.hasPermission("core.echest.other")) {
                Lang.PERMISSION_DENIED.send(sender);
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                Lang.ECHEST_INVALID_PLAYER.send(sender, args[0]);
                return true;
            }
            player.openInventory(target.getEnderChest());
            Lang.ECHEST_OPEN_OTHER.send(sender, target.getName());
            return true;
        }
        Lang.INVALID_ARGS.send(sender, "/" + label + " [player]");

        return true;
    }
}
