package com.valkyrie.hubbasics.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material, int amount) {
        itemStack = new ItemStack(material, amount);
    }


    public ItemBuilder(Material material) {
        this(material, 1);
    }


    public ItemBuilder(Material material, int amount, short value) {
        itemStack = new ItemStack(material, amount, value);
    }

    public ItemBuilder setName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }


    public ItemBuilder setamount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }
    public ItemBuilder enchant(boolean enchanted, Enchantment enchant, int level) {
        if (enchanted) {
            ItemMeta meta = this.itemStack.getItemMeta();
            meta.addEnchant(enchant, level, true);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }
    public ItemBuilder setColor(Color color) {
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta)itemStack.getItemMeta();
        leatherArmorMeta.setColor(color);
        itemStack.setItemMeta(leatherArmorMeta);
        return this;
    }
    public ItemBuilder data(int dur) {
        this.itemStack.setDurability((short) dur);
        return this;
    }

    public ItemStack create() {
        return itemStack;
    }
}