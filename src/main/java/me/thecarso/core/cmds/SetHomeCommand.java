package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
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

        int homeAmount = User.getHomeAmount(player);
        String homeName = args[0].toLowerCase();

        user.getHomes().remove(homeName);

        if (user.getHomes().size() >= homeAmount) {
            Lang.SETHOME_LIMIT.send(sender, homeAmount + "");
            return true;
        }

        user.getHomes().put(homeName, player.getLocation());
        Lang.SETHOME_SUCCESS.send(sender, homeName);
        return true;
    }
}
