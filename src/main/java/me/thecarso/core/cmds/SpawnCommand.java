package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " [player]");
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (Core.getInstance().spawn == null) {
            Lang.SPAWN_MISSING.send(sender);
            return true;
        }
        Player player = (Player) sender;

        TeleportManager.teleportPlayer(player, Core.getInstance().spawn);
        Lang.SPAWN_TELEPORT.send(sender);
        return true;
    }
}
