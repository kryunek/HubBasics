package com.valkyrie.hubbasics.hub.items;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
// NOT WORKING YET
public class TogglePlayers implements Listener {

    public static ArrayList<Player> visibility = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.hasItem()) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().getItemMeta() == null) {
                    return;
                }
                if (event.getItem().getItemMeta().getDisplayName() == null) {
                    return;
                }
                if (CC.translate(HubBasics.get().getConfig().getString("ITEM.HIDEPLAYERS.NAME")).equalsIgnoreCase(event.getItem().getItemMeta().getDisplayName()) ||
                        (CC.translate(HubBasics.get().getConfig().getString("ITEM.SHOWPLAYERS.NAME")).equalsIgnoreCase(event.getItem().getItemMeta().getDisplayName()))) {
                    if (visibility.contains(player)) {
                            visibility.remove(player);
                            player.hidePlayer(player);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("ITEM.SHOWPLAYERS.SOUND")),
                                (float) HubBasics.get().getConfig().getDouble("ITEM.SHOWPLAYERS.VOLUME"),
                                (float) HubBasics.get().getConfig().getDouble("ITEM.SHOWPLAYERS.PITCH"));
                        ItemStack hideplayers = new ItemBuilder(Material.getMaterial(HubBasics.get().getConfig().getString("ITEM.SHOWPLAYERS.MATERIAL")), HubBasics.get().getConfig()
                                .getInt("ITEM.SHOWPLAYERS.AMOUNT"), (short) HubBasics.get().getConfig().getInt("ITEM.SHOWPLAYERS.VALUE"))
                                .setName(CC.translate(HubBasics.get().getConfig().getString("ITEM.SHOWPLAYERS.NAME")))
                                .create();
                        player.getEquipment().setItemInHand(hideplayers);

                    } else if (visibility.contains(player)); {
                            player.showPlayer(player);
                            visibility.add(player);
                        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("ITEM.HIDEPLAYERS.SOUND")),
                                (float) HubBasics.get().getConfig().getDouble("ITEM.HIDEPLAYERS.VOLUME"),
                                (float) HubBasics.get().getConfig().getDouble("ITEM.HIDEPLAYERS.PITCH"));
                        ItemStack showplayers = new ItemBuilder(Material.getMaterial(HubBasics.get().getConfig().getString("ITEM.HIDEPLAYERS.MATERIAL")), HubBasics.get().getConfig()
                                .getInt("ITEM.HIDEPLAYERS.AMOUNT"), (short) HubBasics.get().getConfig().getInt("ITEM.HIDEPLAYERS.VALUE"))
                                .setName(CC.translate(HubBasics.get().getConfig().getString("ITEM.HIDEPLAYERS.NAME")))
                                .create();
                        player.getEquipment().setItemInHand(showplayers);

                    }
                }

            }
        }
    }
}
