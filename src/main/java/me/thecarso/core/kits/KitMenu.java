package me.thecarso.core.kits;

import me.thecarso.core.Core;
import me.thecarso.core.utils.LangUtils;
import me.thecarso.guiapi.ItemBuilder;
import me.thecarso.guiapi.guis.Menu;
import me.thecarso.guiapi.guis.MenuAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class KitMenu extends Menu {
    public static ItemStack filler = new ItemBuilder(Material.BLACK_STAINED_GLASS).setName("&6 ").build();
    public static int[] borders54 = {0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 17,
            18, 26,
            27, 35,
            36, 44,
            45, 46, 47, 48, 49, 50, 51, 52, 53};

    public KitMenu(Player player) {
        super(player);
    }

    @Override
    public String getMenuName() {
        return "Kits";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void setup() {
        for (int i : borders54) {
            addItem(filler, i);
        }
        for (Kit k : Core.getKitManager().getKits().values()) {
            if (player.hasPermission("core.kit." + k.getKitName().toLowerCase())) {
                ItemStack itemStack;
                Sound sound;
                float pitch;
                if (k.canUseKit(player)) {
                    itemStack = new ItemBuilder(Material.CHEST_MINECART)
                            .setName("&d" + k.getKitName())
                            .addLoreLine("&aThis kit is available.").build();
                    pitch = 2;
                    sound = Sound.valueOf("BLOCK_NOTE_BLOCK_CHIME");
                } else {
                    itemStack = new ItemBuilder(Material.MINECART)
                            .setName("&c" + k.getKitName())
                            .addLoreLine("&dThis kit is on cooldown for: ")
                            .addLoreLine("&c  " + LangUtils.getTimeString(k.getCurrentPlayerCooldown(player.getUniqueId()))).build();
                    pitch = .5f;
                    sound = Sound.valueOf("BLOCK_NOTE_BLOCK_BASS");
                }
                addItem(itemStack, new MenuAction() {
                    @Override
                    public void leftClick() {
                        Bukkit.dispatchCommand(player, "kit " + k.getKitName());
                        player.playSound(player.getLocation(), sound, 3, pitch);
                        refresh();
                    }

                    @Override
                    public void rightClick() {
                        Bukkit.dispatchCommand(player, "kit " + k.getKitName());
                        player.playSound(player.getLocation(), sound, 3, pitch);
                        refresh();
                    }

                    @Override
                    public void middleClick() {
                        Bukkit.dispatchCommand(player, "kit " + k.getKitName());
                        player.playSound(player.getLocation(), sound, 3, pitch);
                        refresh();
                    }
                });
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getOpenInventory().getTopInventory().getHolder() instanceof KitMenu)
                    refresh();
            }
        }.runTaskLater(Core.getInstance(), 20);
    }
}
