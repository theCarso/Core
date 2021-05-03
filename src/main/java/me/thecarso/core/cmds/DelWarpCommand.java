package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("core.delwarp")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length != 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <warp name>");
            return true;
        }
        String input = args[0].toLowerCase();
        if (Core.getWarpManager().getWarps().containsKey(input)) {
            String name = Core.getWarpManager().getWarps().get(input).getName();
            Core.getWarpManager().getWarps().remove(input);
            Lang.DELWARP_SUCCESS.send(sender, name);
            return true;
        }
        Lang.DELWARP_ERROR.send(sender, args[0]);
        return true;
    }
}
