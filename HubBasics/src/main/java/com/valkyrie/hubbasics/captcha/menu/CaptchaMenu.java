package com.valkyrie.hubbasics.captcha.menu;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class CaptchaMenu {
    public static void openCaptcha(Player player) {
        Inventory inventory = Bukkit.createInventory(null, HubBasics.get().getConfig().getInt("CAPTCHA.MENU.SIZE"), CC.translate(HubBasics.get().getConfig().getString("CAPTCHA.MENU.NAME")));
        Random random = new Random();
        int rand = random.nextInt(HubBasics.get().getConfig().getInt("CAPTCHA.MENU.SIZE"));

        for(int i = 0; i < HubBasics.get().getConfig().getInt("CAPTCHA.MENU.SIZE"); i++){
            if(!(rand == i))
                inventory.setItem(i, new ItemBuilder (Material.getMaterial(
                        HubBasics.get().getConfig().getString("CAPTCHA.MENU.FAIL_MATERIAL")), HubBasics.get().getConfig()
                        .getInt("CAPTCHA.MENU.FAIL_AMOUNT"), (short) HubBasics.get().getConfig().getInt("CAPTCHA.MENU.FAIL_VALUE"))
                        .setName(CC.translate(HubBasics.get().getConfig().getString("CAPTCHA.MENU.FAIL_NAME")))
                        .create());
        }

        inventory.setItem(rand, new ItemBuilder (Material.getMaterial(
                HubBasics.get().getConfig().getString("CAPTCHA.MENU.PASS_MATERIAL")), HubBasics.get().getConfig()
                .getInt("CAPTCHA.MENU.PASS_AMOUNT"), (short) HubBasics.get().getConfig().getInt("CAPTCHA.MENU.PASS_VALUE"))
                .setName(CC.translate(HubBasics.get().getConfig().getString("CAPTCHA.MENU.PASS_NAME")))
                .create());
        Bukkit.getScheduler().runTask(HubBasics.get(), () -> player.openInventory(inventory));
    }
}