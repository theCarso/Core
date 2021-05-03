package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.warps.Warp;
import me.thecarso.core.warps.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <warp name> <permission?>");
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            StringBuilder sb = new StringBuilder();
            for (String string : Core.getWarpManager().getAvailableWarps(player)) {
                sb.append(string).append(", ");
            }
            if (sb.length() == 0) {
                Lang.WARP_NONE_FOUND.send(player);
                return true;
            }
            String list = sb.substring(0, sb.length() - 2);
            Lang.WARP_LIST.send(sender, list);
            return true;
        }
        if (Core.getWarpManager().getWarps().containsKey(args[0].toLowerCase())) {
            Warp warp = Core.getWarpManager().getWarps().get(args[0].toLowerCase());
            if (warp.getPermission()!=null && !player.hasPermission(warp.getPermission())) {
                Lang.WARP_NO_PERMISSION.send(sender);
                return true;
            }
            warp.teleport(player);
            return true;
        }
        Lang.WARP_INVALID.send(sender, args[0]);
        return true;
    }
}
