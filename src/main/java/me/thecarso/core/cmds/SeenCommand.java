package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.seen")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (args.length != 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <name>");
            return true;
        }
        OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
        if (!player.hasPlayedBefore()) {
            Lang.SEEN_INVALID_PLAYER.send(sender, args[0]);
            return true;
        }
        if(player.isOnline()){
            Lang.SEEN_ONLINE.send(sender, player.getName());
            return true;
        }
        Lang.SEEN_SUCCESS.send(sender, player.getName(), LangUtils.getSeenString(player.getLastPlayed()));
        return true;
    }
}
