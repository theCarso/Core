package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.broadcast")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length == 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " [message]");
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        LangUtils.broadcastPrefixedMessage(sb.toString().trim());
        return true;
    }
}
