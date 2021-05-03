package me.thecarso.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public List<String> lore;

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
        lore = new ArrayList<>();
    }

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder setType(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String string) {
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', string));
        return this;
    }

    public ItemBuilder resetName() {
        itemMeta.setDisplayName("");
        return this;
    }

    public ItemBuilder setLore(List<String> newLore) {
        lore = newLore;
        return this;
    }

    public ItemBuilder addLoreLine(String string) {
        lore.add(ChatColor.translateAlternateColorCodes('&', string));
        return this;
    }

    public ItemBuilder removeLoreLine(int index) {
        lore.remove(index);
        return this;
    }

    public ItemBuilder resetLore() {
        lore.clear();
        return this;
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return itemMeta.getEnchants();
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        itemMeta.removeEnchant(enchantment);
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        if (itemMeta instanceof Damageable)
            ((Damageable) itemMeta).setDamage(durability);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean bool) {
        itemMeta.setUnbreakable(bool);
        return this;
    }

    public ItemStack build() {
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
