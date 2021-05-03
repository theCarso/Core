package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (args.length != 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <home name>");
            return true;
        }
        Player player = (Player) sender;
        User user = Core.getUserManager().getUser(player);
        String homeName = args[0].toLowerCase();

        if (user.getHomes().containsKey(homeName)) {
            user.getHomes().remove(homeName);
            Lang.DELHOME_SUCCESS.send(sender, homeName);
            return true;
        }
        Lang.DELHOME_INVALID.send(sender, homeName);
        return true;
    }
}
