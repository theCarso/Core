package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("core.god")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }

        Player player = (Player) sender;
        player.setInvulnerable(!player.isInvulnerable());
        if(player.isInvulnerable()){
            Lang.GOD_SUCCESS.send(sender, "&aenabled");
        }else{
            Lang.GOD_SUCCESS.send(sender, "&cdisabled");
        }
        return true;
    }
}
