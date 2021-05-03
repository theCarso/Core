package me.thecarso.core.kits;

import lombok.Getter;
import me.thecarso.core.Core;
import me.thecarso.core.utils.CFile;
import me.thecarso.core.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Kit {
    private @Getter
    HashMap<UUID, Long> cooldownEnd;

    private @Getter
    String kitName;
    private long cooldownInSeconds;

    private CFile file;

    private ArrayList<ItemStack> items;
    private ArrayList<String> commands;

    public Kit(String kitName, CFile file) {
        cooldownEnd = new HashMap<>();
        items = new ArrayList<>();
        commands = new ArrayList<>();
        this.kitName = kitName;
        this.file = file;

        loadItems();
        loadCommands();
        loadCooldowns();
    }

    private void loadItems() {
        if (file.getFile().isSet("items")) {
            for (String string : file.getFile().getConfigurationSection("items").getKeys(false)) {
                items.add(craftItemFromSection(file.getFile().getConfigurationSection("items." + string)));
            }
        }
    }

    private void loadCommands() {
        if (file.getFile().isSet("commands")) {
            commands.addAll(file.getFile().getStringList("commands"));
        }
    }

    private void loadCooldowns() {
        cooldownInSeconds = file.getFile().getLong("cooldownInSeconds");
        if (file.getFile().isSet("cooldowns")) {
            for (String string : file.getFile().getConfigurationSection("cooldowns").getKeys(false)) {
                UUID playerUUID = UUID.fromString(string);
                long cooldownTime = file.getFile().getLong("cooldowns." + string);

                cooldownEnd.put(playerUUID, cooldownTime);
            }
        }
    }

    public boolean giveKit(Player player, boolean force) {
        if (canUseKit(player) || force) {
            for (ItemStack item : items) {
                player.getInventory().addItem(item.clone());
            }
            for (String string : commands) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string.replace("%player%", player.getName()));
            }

            if (!force) {
                cooldownEnd.put(player.getUniqueId(), System.currentTimeMillis() + (cooldownInSeconds * 1000));
            }
            return true;
        }
        return false;
    }

    public long getCurrentPlayerCooldown(UUID uuid) {
        if (!cooldownEnd.containsKey(uuid)) return 0;
        return cooldownEnd.get(uuid);
    }

    public boolean canUseKit(Player player) {
        return getCurrentPlayerCooldown(player.getUniqueId()) <= System.currentTimeMillis() ||
                player.hasPermission("core.kits.admin");
    }

    public void saveKit() {
        File testingFile = new File(Core.getInstance().getDataFolder(), "kits/" + kitName + ".yml");
        if (testingFile.exists()) {
            file = new CFile(Core.getInstance(), "kits/" + kitName, false);
            for (UUID uuid : cooldownEnd.keySet()) {
                file.getFile().set("cooldowns." + uuid.toString(), cooldownEnd.get(uuid));
            }
            file.saveFile();
        }
    }

    private static ItemStack craftItemFromSection(ConfigurationSection section) {
        Material material = Material.getMaterial(section.getString("type"));
        int data = section.getInt("data");
        int amount = section.getInt("amount");
        ItemBuilder itemBuilder = new ItemBuilder(material, amount).setDurability(data);
        if (section.isSet("name")) {
            itemBuilder.setName(section.getString("name"));
        }
        if (section.isSet("lore")) {
            for (String string : section.getStringList("lore")) {
                itemBuilder.addLoreLine(string);
            }
        }
        if (section.isSet("enchants")) {
            for (String string : section.getStringList("enchants")) {
                String[] split = string.split(":");
                itemBuilder.addEnchantment(Enchantment.getByName(split[0]), Integer.parseInt(split[1]));
            }
        }
        ItemStack item = itemBuilder.build();
        item.setAmount(amount);
        return item;

    }
}
