package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.back")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (args.length != 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label);
            return true;
        }
        Player player = (Player) sender;
        Location lastLocation = TeleportManager.getLastTeleport().get(player.getUniqueId());
        if (lastLocation == null) {
            Lang.BACK_NOTFOUND.send(sender);
            return true;
        }
        TeleportManager.teleportPlayer(player, TeleportManager.getLastTeleport().get(player.getUniqueId()));
        Lang.BACK_SUCCESS.send(sender);
        return true;
    }
}
