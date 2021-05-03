package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (args.length > 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <home name>");
            return true;
        }
        Player player = (Player) sender;
        User user = Core.getUserManager().getUser(player);
        if (args.length == 0) {
            StringBuilder sb = new StringBuilder();
            for (String string : user.getHomes().keySet()) {
                sb.append(string).append(", ");
            }
            if(sb.length() <= 0){
                Lang.HOME_NONE.send(sender);
                return true;
            }
            String built = sb.toString().trim();
            built = built.substring(0, built.length()-1);
            Lang.HOME_LIST.send(sender, built);
            return true;
        }
        String homeName = args[0].toLowerCase();

        if (user.getHomes().containsKey(homeName)) {
            TeleportManager.teleportPlayer(player, user.getHomes().get(homeName));
            Lang.HOME_SUCCESS.send(sender, homeName);
            return true;
        }
        Lang.HOME_INVALID.send(sender, homeName);
        return true;
    }
}
