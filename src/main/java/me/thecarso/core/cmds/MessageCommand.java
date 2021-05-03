package me.thecarso.core.cmds;

import lombok.Getter;
import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MessageCommand implements CommandExecutor {

    public static
    HashMap<String, String> lastReply = new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <player> <message>");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            Lang.MESSAGE_INVALID_PLAYER.send(sender, args[0]);
            return true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]).append(' ');
        }

        Lang.MESSAGE_TARGET.send(target, sender.getName(), sb.toString().trim());
        Lang.MESSAGE_SENDER.send(sender, target.getName(), sb.toString().trim());
        lastReply.put(sender.getName(), target.getName());
        lastReply.put(target.getName(), sender.getName());
        for (UUID uuid : SocialSpyCommand.socialSpy) {
            Player ss = Bukkit.getPlayer(uuid);
            if (ss != null) {
                if (!(ss.getName().equalsIgnoreCase(target.getName()) || ss.getName().equalsIgnoreCase(sender.getName()))) {
                    Lang.SOCIALSPY_VIEW.send(ss, sender.getName(), target.getName(), sb.toString().trim());
                }
            }
        }

        return true;
    }
}
