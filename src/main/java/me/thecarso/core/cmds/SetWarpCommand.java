package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.warps.Warp;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("core.setwarp")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length != 2) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <warp name> <permission?>");
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        String name = args[0];
        boolean permission = args[1].equalsIgnoreCase("true");
        Location loc = player.getLocation();

        Core.getWarpManager().getWarps().put(name.toLowerCase(), new Warp(name, loc, permission));
        Lang.SETWARP_SUCCESS.send(sender, name);
        return true;
    }
}
