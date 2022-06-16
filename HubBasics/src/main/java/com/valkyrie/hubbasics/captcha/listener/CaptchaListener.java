package com.valkyrie.hubbasics.captcha.listener;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CaptchaListener implements Listener {

    private final HubBasics plugin;

    public CaptchaListener(HubBasics plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCaptchaClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        if (event.getInventory().getName().equals(CC.translate(HubBasics.get().getConfig().getString("CAPTCHA.MENU.NAME")))) {
            if (!HubBasics.getPlayerArrayList().contains(player))
                player.kickPlayer(CC.translate(plugin.getConfig().getString("CAPTCHA.FAILED")));
        }
    }

    @EventHandler
    public void onCaptchaClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getTitle().equals(CC.translate(HubBasics.get().getConfig().getString("CAPTCHA.MENU.NAME")))) {
            event.setCancelled(true);

            if (!event.getCurrentItem().getType().equals((Material.getMaterial(
                    HubBasics.get().getConfig().getString("CAPTCHA.MENU.PASS_MATERIAL"))))) {
                player.kickPlayer(CC.translate(plugin.getConfig().getString("CAPTCHA.FAILED")));
                return;
            }

            HubBasics.getPlayerArrayList().add(player);
            player.closeInventory();
            player.sendMessage(CC.translate(plugin.getConfig().getString("CAPTCHA.PASSED")));
            player.playSound(player.getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("CAPTCHA.MENU.SOUND")),
                    (float) HubBasics.get().getConfig().getDouble("CAPTCHA.MENU.VOLUME"),
                    (float) HubBasics.get().getConfig().getDouble("CAPTCHA.MENU.PITCH"));
        }
    }
}