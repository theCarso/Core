package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // NO PERMISSION
        if (!sender.hasPermission("core.gamemode")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        // INVALID ARGUMENTS
        if (args.length == 0 || args.length > 2) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <mode> [player]");
            return true;
        }

        // GET GAMEMODE
        GameMode mode = null;
        switch (args[0].toLowerCase()) {
            case "creative":
            case "c":
                mode = GameMode.CREATIVE;
                break;
            case "survival":
            case "s":
                mode = GameMode.SURVIVAL;
                break;
            case "spectator":
            case "sp":
                mode = GameMode.SPECTATOR;
            case "adventure":
            case "a":
                mode = GameMode.ADVENTURE;
        }
        if (mode == null) {
            Lang.GAMEMODE_INVALID_MODE.send(sender);
            return true;
        }

        // 2 ARGS /gamemode <mode> <player>
        if (args.length == 2) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                Lang.GAMEMODE_INVALID_PLAYER.send(sender, args[1]);
                return true;
            }
            target.setGameMode(mode);
            Lang.GAMEMODE_SUCCESS_SENDER.send(sender, target.getName(), mode.name().toLowerCase());
            Lang.GAMEMODE_SUCCESS_TARGET.send(target, mode.name().toLowerCase());
            return true;
        }

        // 1 ARG sender check
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }

        ((Player) sender).setGameMode(mode);
        Lang.GAMEMODE_SUCCESS_TARGET.send(sender, mode.name().toLowerCase());
        return true;
    }
}
