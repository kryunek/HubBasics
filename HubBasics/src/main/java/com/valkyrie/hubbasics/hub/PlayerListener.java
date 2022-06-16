package com.valkyrie.hubbasics.hub;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.hub.features.Armor;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.ItemBuilder;
import com.valkyrie.hubbasics.utils.LocationUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    private final String bullet_point = "•", right_arrow = "»", left_arrow = "«";


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setGameMode(GameMode.SURVIVAL);
        if(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")) != null){
            player.teleport(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")));

        } else {
            Bukkit.getConsoleSender().sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("spawn.no-spawn")));
        }
        List<String> server_lore = new ArrayList<>();
        ItemStack server_selector = new ItemBuilder(Material.getMaterial(HubBasics.get().getConfig().getString("ITEM.SERVER_SELECTOR.MATERIAL")), HubBasics.get().getConfig()
                .getInt("ITEM.SERVER_SELECTOR.AMOUNT"), (short) HubBasics.get().getConfig().getInt("ITEM.SERVER_SELECTOR.VALUE"))
                .setName(CC.translate(HubBasics.get().getConfig().getString("ITEM.SERVER_SELECTOR.NAME")))
                .create();
        player.getInventory().setItem(HubBasics.get().getConfig().getInt("ITEM.SERVER_SELECTOR.SLOT"), server_selector);

        ItemStack enderbutt = new ItemBuilder(Material.getMaterial(HubBasics.get().getConfig().getString("ITEM.ENDER_BUTT.MATERIAL")), HubBasics.get().getConfig()
                .getInt("ITEM.ENDER_BUTT.AMOUNT"), (short) HubBasics.get().getConfig().getInt("ITEM.ENDER_BUTT.VALUE"))
                .setName(CC.translate(HubBasics.get().getConfig().getString("ITEM.ENDER_BUTT.NAME")))
                .create();
        player.getInventory().setItem(HubBasics.get().getConfig().getInt("ITEM.ENDER_BUTT.SLOT"), enderbutt);
        Armor.getConfigArmorAndGenerate(player);




        for(int i = 0; i < 100; i++) { player.sendMessage(""); }

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            for(String list : HubBasics.get().getConfig().getStringList("WELCOME_MESSAGE")){
                player.sendMessage(PlaceholderAPI.setPlaceholders(player, CC.translate(list)
                        .replace("%bullet_point%", "•")));
            }
        } else {
            for(String list : HubBasics.get().getConfig().getStringList("WELCOME_MESSAGE")){
                player.sendMessage(CC.translate(list).replace("%bullet_point%", "•"));
            }
        }
        if(HubBasics.get().getConfig().getBoolean("PLAYERJOIN.ENABLE-SOUND"))
        if (player.hasPermission(HubBasics.get().getConfig().getString("PLAYERJOIN.PERMISSION-SOUND")))
        player.playSound(player.getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("PLAYERJOIN.SOUND")),
                (float) HubBasics.get().getConfig().getDouble("PLAYERJOIN.VOLUME"),
                (float) HubBasics.get().getConfig().getDouble("PLAYERJOIN.PITCH"));
        if(HubBasics.get().getConfig().getBoolean("PLAYERJOIN.ENABLE"))
        if (player.hasPermission(HubBasics.get().getConfig().getString("PLAYERJOIN.PERMISSION")))
            event.setJoinMessage(CC.translate(HubBasics.get().getConfig().getString("PLAYERJOIN.MESSAGE")).replace("{player}", player.getDisplayName()));


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(null);
        if(HubBasics.get().getConfig().getBoolean("PLAYERQUIT.ENABLE"))
            if (player.hasPermission(HubBasics.get().getConfig().getString("PLAYERQUIT.PERMISSION")))
                event.setQuitMessage(CC.translate(HubBasics.get().getConfig().getString("PLAYERQUIT.MESSAGE")).replace("{player}", player.getDisplayName()));
    }


    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if(HubBasics.get().getConfig().getBoolean("WEATHER"))
        event.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if(HubBasics.get().getConfig().getBoolean("FOOD"))
        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(HubBasics.get().getConfig().getBoolean("DAMAGE"))
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if(HubBasics.get().getConfig().getBoolean("ENTITY"))
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getWhoClicked().getGameMode().equals(GameMode.SURVIVAL) || !event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
        event.setCancelled(true);
    }
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
    }
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("PICKUP"))
            if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.pickup")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("DROP"))
        if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.drop")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("PLACE"))
        if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.place")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("BREAK"))
        if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.break")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        }

    @EventHandler
    public void bucketFill(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("BUCKETEMPTY"))
        if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.bucketfill")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("BUCKETFILL"))
        if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.bucketempty")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
    }
    @EventHandler
    public void playerFish(PlayerFishEvent event){
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("FISH"))
        if(!player.hasPermission(HubBasics.get().getPermissionsYml().getString("HUB.fish")) || !event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
        event.setCancelled(true);
    }
    @EventHandler
    public void entityExplode(EntityExplodeEvent event){
        if(HubBasics.get().getConfig().getBoolean("EXPLODE"))
        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getLocation().getBlockY() < 0) {

            if(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")) != null){
                player.teleport(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")));

            } else {
                Bukkit.getConsoleSender().sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("spawn.no-spawn")));
            }

            if(HubBasics.get().getConfig().getBoolean("SPAWN.VOID.ENABLE")){
                player.teleport(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")));
                player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("spawn.message")));
            }
        }
    }
}
