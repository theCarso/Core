package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("core.fly")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }

        if (args.length > 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " [player]");
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                Lang.CONSOLE_SENDER_ERROR.send(sender);
                return true;
            }
            Player player = (Player) sender;
            player.setAllowFlight(!player.getAllowFlight());
            String status = (player.getAllowFlight() ? "enabled" : "disabled");
            Lang.FLY_SUCCESS_TARGET.send(sender, status);
            return true;
        }
        if(!sender.hasPermission("core.fly.others")){
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            Lang.FLY_INVALID_PLAYER.send(sender, args[0]);
            return true;
        }
        target.setAllowFlight(!target.getAllowFlight());
        String status = (target.getAllowFlight() ? "enabled" : "disabled");
        Lang.FLY_SUCCESS_TARGET.send(target, status);
        Lang.FLY_SUCCESS_SENDER.send(sender, status, target.getName());
        return true;
    }
}
