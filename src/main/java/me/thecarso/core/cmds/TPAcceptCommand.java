package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TPRequest;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAcceptCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        if (TPRequest.getTpRequests().containsKey(player.getName())) {
            TPRequest request = TPRequest.getTpRequests().get(player.getName());
            String fromName = request.getFrom();
            Player from = Bukkit.getPlayer(fromName);

            if (from == null || !from.isOnline()) {
                Lang.TPACCEPT_OFFLINE.send(sender, fromName);
                return true;
            }
            if (request.getType() == TPRequest.TPType.TPA) {
                Lang.TPACCEPT_SENDER.send(player, from.getName());
                Lang.TPACCEPT_TARGET.send(from, player.getName());
                TeleportManager.teleportPlayer(from, player.getLocation());
            } else {
                Lang.TPACCEPT_TARGET.send(player, from.getName());
                Lang.TPACCEPT_SENDER.send(from, player.getName());
                TeleportManager.teleportPlayer(player, from.getLocation());
            }
            TPRequest.getTpRequests().remove(player.getName());
            return true;
        }
        Lang.TPACCEPT_NOREQUEST.send(sender);
        return true;
    }
}
