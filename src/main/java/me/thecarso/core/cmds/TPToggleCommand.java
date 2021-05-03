package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPToggleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (!sender.hasPermission("core.tptoggle")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length != 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label + "");
            return true;
        }
        Player player = (Player) sender;
        User user = Core.getUserManager().getUser(player);
        user.setTpEnabled(!user.isTpEnabled());
        if(user.isTpEnabled()){
            Lang.TPTOGGLE_SUCCESS.send(sender, "&aenabled");
        }else{
            Lang.TPTOGGLE_SUCCESS.send(sender, "&cdisabled");
        }
        return true;
    }
}
