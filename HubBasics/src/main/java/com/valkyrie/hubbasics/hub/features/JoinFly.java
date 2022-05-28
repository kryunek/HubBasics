package com.valkyrie.hubbasics.hub.features;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.commands.essentials.FlyCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinFly implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("FLYJOIN.ENABLE"))
            if (player.hasPermission(HubBasics.get().getConfig().getString("FLYJOIN.PERMISSION")))
                FlyCommand.flying.add(player);
                player.setAllowFlight(true);
    }
    public void onLeave(PlayerJoinEvent event) {
        Player player = event.getPlayer();
                FlyCommand.flying.remove(player);
        player.setAllowFlight(false);
    }
}