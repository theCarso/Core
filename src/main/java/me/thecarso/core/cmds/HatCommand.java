package me.thecarso.core.cmds;

import me.thecarso.core.utils.Lang;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("core.hat")) {
            Lang.PERMISSION_DENIED.send(sender);
            return true;
        }
        if (args.length > 0) {
            Lang.INVALID_ARGS.send(sender, "/" + label);
            return true;
        }
        if (!(sender instanceof Player)) {
            Lang.CONSOLE_SENDER_ERROR.send(sender);
            return true;
        }
        Player player = (Player) sender;
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (hand != null && hand.getType()!= Material.AIR) {
            ItemStack hat = player.getInventory().getHelmet();

            player.getInventory().setHelmet(hand);
            player.getInventory().setItemInMainHand(hat);
            Lang.HAT_SUCCESS.send(sender);
            return true;
        }
        Lang.HAT_NOTHING_IN_HAND.send(sender);
        return true;
    }
}
