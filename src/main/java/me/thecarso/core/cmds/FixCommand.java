package me.thecarso.core.cmds;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class FixCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        if (!sender.hasPermission("core.fix")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length != 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label);
            return true;
        }
        Player player = (Player) sender;
        User user = Core.getUserManager().getUser(player);
        if (!player.hasPermission("core.fix.bypass")) {
            if (user.getNextFix() > System.currentTimeMillis()) {
                Lang.FIX_COOLDOWN.send(sender, LangUtils.getTimeString(user.getNextFix()));
                return true;
            }
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack != null) {
            if (itemStack.getType().getMaxDurability() > 0) {
                if (itemStack.getDurability() > 0) {
                    player.getInventory().getItemInMainHand().setDurability((short) 0);
                    user.setNextFix(System.currentTimeMillis() + (1000*60*60*24));
                    Lang.FIX_SUCCESS.send(sender);
                    return true;
                }
            }
        }
        Lang.FIX_INVALID.send(sender);

        return true;
    }
}
