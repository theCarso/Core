package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class SocialSpyCommand implements CommandExecutor {
    public static ArrayList<UUID> socialSpy = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.socialspy")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (args.length > 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " [player]");
            return true;
        }
        Player player = (Player) sender;
        if (!socialSpy.contains(player.getUniqueId())) {
            socialSpy.add(player.getUniqueId());
            Lang.SOCIALSPY_TOGGLED.send(sender, "&aenabled");
        } else {
            socialSpy.remove(player.getUniqueId());
            Lang.SOCIALSPY_TOGGLED.send(sender, "&cdisabled");
        }
        return true;
    }
}
