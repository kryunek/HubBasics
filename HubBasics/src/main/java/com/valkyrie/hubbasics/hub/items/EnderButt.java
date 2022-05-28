package com.valkyrie.hubbasics.hub.items;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnderButt implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.hasItem()) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getItem().getItemMeta() == null) { return; }
                if (e.getItem().getItemMeta().getDisplayName() == null) { return; }
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(CC.translate(HubBasics.get().getConfig().getString("ITEM.ENDER_BUTT.NAME")))){
                    e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(2.5F));

                    e.setCancelled(true);
                    e.setUseItemInHand(Event.Result.DENY);
                    e.getPlayer().updateInventory();

                    try {
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("ITEM.ENDER_BUTT.SOUND")),
                                (float) HubBasics.get().getConfig().getDouble("ITEM.ENDER_BUTT.VOLUME"),
                                (float) HubBasics.get().getConfig().getDouble("ITEM.ENDER_BUTT.PITCH"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

}