package me.thecarso.core.kits;

import lombok.Getter;
import me.thecarso.core.Core;
import me.thecarso.core.utils.CFile;
import me.thecarso.core.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class KitManager {

    private @Getter
    HashMap<String, Kit> kits;

    public KitManager() {
        kits = new HashMap<>();

        loadKits();
    }

    private void loadKits() {
        File folder = new File(Core.getInstance().getDataFolder(), "kits");
        if (!folder.exists()) {
            folder.mkdir();
            new CFile(Core.getInstance(), "kits/default", true).saveFile();
        }
        File[] files = folder.listFiles();
        if (files == null) return;
        for (File file : files) {
            String kitName = file.getName().replace(".yml", "");
            CFile kitFile = new CFile(Core.getInstance(), "kits/" + kitName, false);
            kits.put(kitName.toLowerCase(), new Kit(kitName, kitFile));
        }
    }

    public String getKitList(Player player) {
        StringBuilder sb = new StringBuilder();
        for (String string : kits.keySet()) {
            Kit toCheck = kits.get(string);
            if (player.hasPermission("core.kit." + toCheck.getKitName().toLowerCase())) {
                if (toCheck.canUseKit(player)) sb.append("&e");
                else sb.append("&c");
                sb.append(toCheck.getKitName()).append("&d, ");
            }
        }
        if (sb.length() == 0) return "";
        return sb.substring(0, sb.length() - 2);
    }

}
