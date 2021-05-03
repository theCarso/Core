package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TPRequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPDenyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        if (TPRequest.getTpRequests().containsKey(player.getName())) {
            TPRequest request = TPRequest.getTpRequests().get(player.getName());
            String from = request.getFrom();
            Player target = Bukkit.getPlayer(from);

            Lang.TPDENY_SENDER.send(sender, from);
            if (target != null && target.isOnline()) {
                Lang.TPDENY_TARGET.send(target, sender.getName());
            }
            TPRequest.getTpRequests().remove(player.getName());
            return true;
        }
        Lang.TPDENY_NOREQUEST.send(sender);
        return true;
    }
}
