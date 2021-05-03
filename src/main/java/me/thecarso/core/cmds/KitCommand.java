package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.kits.Kit;
import me.thecarso.core.kits.KitMenu;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 2) {
            Lang.INVALID_ARGS.send(sender, "/" + label + " <kit> [player]");
            return true;
        }
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                Lang.CONSOLE_SENDER_ERROR.send(sender);
                return true;
            }
            Player player = (Player) sender;
            new KitMenu(player).open();
            /*String list = Core.getKitManager().getKitList(player);
            if (list.isEmpty()) {
                Lang.KIT_LIST_EMPTY.send(player);
                return true;
            }
            Lang.KIT_LIST.send(player, list);*/
            return true;
        }
        if (!Core.getKitManager().getKits().containsKey(args[0].toLowerCase())) {
            Lang.KIT_NOT_EXIST.send(sender, args[0]);
            return true;
        }
        Kit kit = Core.getKitManager().getKits().get(args[0].toLowerCase());
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                Lang.CONSOLE_SENDER_ERROR.send(sender);
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("core.kit." + kit.getKitName().toLowerCase())) {
                Lang.KIT_NO_PERMISSION.send(sender);
                return true;
            }
            if (kit.giveKit(player, false)) {
                Lang.KIT_RECEIVED.send(sender, kit.getKitName());
                return true;
            }
            Lang.KIT_COOLDOWN.send(sender, LangUtils.getTimeString(kit.getCurrentPlayerCooldown(player.getUniqueId())));
            return true;
        }
        if(!sender.hasPermission("core.kit.others")){
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            Lang.KIT_INVALID_PLAYER.send(sender, args[1]);
            return true;
        }
        kit.giveKit(target, true);
        Lang.KIT_RECEIVED.send(target, kit.getKitName());
        Lang.KIT_SENT.send(sender, target.getName(), kit.getKitName());


        return false;
    }
}
