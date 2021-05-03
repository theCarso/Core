package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendRawCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.sendraw")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length < 2) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <player> <message>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                sb.append(args[i]).append(" ");
            }
            LangUtils.sendMessage(target, sb.toString().trim());
        }
        return true;
    }
}
