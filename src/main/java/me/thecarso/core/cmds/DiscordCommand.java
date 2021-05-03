package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Lang.DISCORD_SERVER.send(sender, Core.getConfigFile().getFile().getString("discord"));
        return true;
    }
}
