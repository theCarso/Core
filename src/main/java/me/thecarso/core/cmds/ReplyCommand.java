package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            Lang.INVALID_ARGS.send(sender, "/" + label + "<message>");
            return true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(' ');
        }
        if (MessageCommand.lastReply.containsKey(sender.getName())) {
            Player target = Bukkit.getPlayer(MessageCommand.lastReply.get(sender.getName()));
            if (target != null) {

                Lang.MESSAGE_TARGET.send(target, sender.getName(), sb.toString().trim());
                Lang.MESSAGE_SENDER.send(sender, target.getName(), sb.toString().trim());

                MessageCommand.lastReply.put(sender.getName(), target.getName());
                MessageCommand.lastReply.put(target.getName(), sender.getName());
                for (UUID uuid : SocialSpyCommand.socialSpy) {
                    Player ss = Bukkit.getPlayer(uuid);
                    if (ss != null) {
                        Lang.SOCIALSPY_VIEW.send(ss, sender.getName(), target.getName(), sb.toString().trim());
                    }
                }
                return true;
            }
        }
        Lang.MESSAGE_REPLY_INVALID.send(sender);
        return true;
    }
}
