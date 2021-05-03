package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("core.setspawn")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length > 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        Core.getInstance().setSpawn(player.getLocation());
        Lang.SETSPAWN_SUCCESS.send(player);

        return true;
    }
}
