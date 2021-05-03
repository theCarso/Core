package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.tp")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (args.length == 0 || args.length > 2) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <player> OR /" +label + " <fromPlayer> <toPlayer>");
            return true;
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                Lang.TP_INVALID_PLAYER.send(sender, args[0]);
                return true;
            }
            Player player = (Player) sender;
            TeleportManager.teleportPlayer(player, target.getLocation());
            Lang.TP_SUCCESS.send(sender, target.getName());
            return true;
        }
        Player from = Bukkit.getPlayer(args[0]);
        Player to = Bukkit.getPlayer(args[1]);
        if(from == null){
            Lang.TP_INVALID_PLAYER.send(sender, args[0]);
            return true;
        }
        if(to == null){
            Lang.TP_INVALID_PLAYER.send(sender, args[1]);
            return true;
        }
        TeleportManager.teleportPlayer(from, to.getLocation());
        Lang.TP_SUCCESS.send(from, to.getName());
        Lang.TP_OTHERS.send(sender, from.getName(), to.getName());
        return true;
    }
}
