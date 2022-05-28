package com.valkyrie.hubbasics.hub.items;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.BungeeUtil;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ServerSelector implements Listener {

    private final String bullet_point = "•", right_arrow = "»", left_arrow = "«";

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getItem() == null) {
            return;

        } else {

            if (CC.translate(HubBasics.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")).equalsIgnoreCase(event.getItem().getItemMeta().getDisplayName())) {
                Inventory inventory = Bukkit.createInventory(null, HubBasics.get().getConfig().getInt("SERVER_SELECTOR.SIZE"), CC.translate(HubBasics.get().getConfig().getString("SERVER_SELECTOR.NAME")));
                player.openInventory(inventory);
                IntStream.range(0, HubBasics.get().getConfig().getInt("SERVER_SELECTOR.SIZE")).forEach(i -> inventory.setItem(i, new ItemBuilder(Material.getMaterial(
                        HubBasics.get().getConfig().getString("SERVER_SELECTOR_FILLER.MATERIAL")), HubBasics.get().getConfig()
                        .getInt("SERVER_SELECTOR_FILLER.AMOUNT"), (short) HubBasics.get().getConfig().getInt("SERVER_SELECTOR_FILLER.VALUE"))
                        .setName(HubBasics.get().getConfig().getString("SERVER_SELECTOR_FILLER.NAME"))
                        .create()));

                for (Map.Entry<String, Object> entry : HubBasics.get().getConfig().getConfigurationSection("SERVER_SELECTOR.SERVERS.").getValues(false).entrySet()) {
                    String path = "SERVER_SELECTOR.SERVERS." + entry.getKey(); // can be ARMORS.1, ARMORS.2, etc
                List<String> server_lore = new ArrayList<>();
                HubBasics.get().getConfig().getStringList(path + ".LORE").forEach(string -> server_lore.add(CC.translate(string
                                .replace("%bullet_point%", bullet_point))
                        .replace("%right_arrow%", right_arrow)
                        .replace("%left_arrow%", left_arrow)));
                ItemStack destination = new ItemBuilder(Material.getMaterial(HubBasics.get().getConfig().getString(path + ".MATERIAL")))
                        .setName(CC.translate(HubBasics.get().getConfig().getString(path + ".NAME")))
                        .setLore(server_lore)
                        .create();
                inventory.setItem(HubBasics.get().getConfig().getInt(path + ".SLOT"), destination);
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("ITEM.SERVER_SELECTOR.SOUND")),
                        (float) HubBasics.get().getConfig().getDouble("ITEM.SERVER_SELECTOR.VOLUME"),
                        (float) HubBasics.get().getConfig().getDouble("ITEM.SERVER_SELECTOR.PITCH"));
                        player.updateInventory();
            }
        }
    }

}

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        for (Map.Entry<String, Object> entry : HubBasics.get().getConfig().getConfigurationSection("SERVER_SELECTOR.SERVERS.").getValues(false).entrySet()) {
            String path = "SERVER_SELECTOR.SERVERS." + entry.getKey(); // can be ARMORS.1, ARMORS.2, etc
            if (event.getInventory() != null && CC.translate(HubBasics.get().getConfig().getString("SERVER_SELECTOR.NAME")).equalsIgnoreCase(event.getInventory().getName())) {
                if (CC.translate(HubBasics.get().getConfig().getString(path + ".NAME")).equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
                    BungeeUtil.sendToServer(player, HubBasics.get().getConfig().getString(path + ".SERVER"));

                    if (CC.translate(HubBasics.get().getConfig().getString(path + ".NAME")).equalsIgnoreCase(String.valueOf(event.getCurrentItem().getItemMeta().getDisplayName() == null)))
                        event.setCancelled(true);
                }
            }
        }
    }
}