package me.thecarso.core.cmds;

import lombok.Getter;
import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TPRequest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TPACommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <player>");
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == sender) {
            Lang.TPA_SELF.send(sender);
            return true;
        }
        if (target == null) {
            Lang.TPA_INVALID_PLAYER.send(sender, args[0]);
            return true;
        }
        User user = Core.getUserManager().getUser(target);
        if (!user.isTpEnabled()) {
            Lang.TPA_TOGGLED.send(sender, target.getName());
            return true;
        }
        if (TPRequest.getTpRequests().containsKey(target.getName()) && TPRequest.getTpRequests().get(target.getName()).getFrom().equalsIgnoreCase(sender.getName())) {
            Lang.TPA_SPAM.send(sender, target.getName());
            return true;
        }
        TPRequest.getTpRequests().put(target.getName(), new TPRequest(sender.getName(), target.getName(), TPRequest.TPType.TPA));
        Lang.TPA_SENT.send(sender, target.getName());
        Lang.TPA_RECEIVED.send(target, sender.getName());

        return true;
    }
}
