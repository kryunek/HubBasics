package com.valkyrie.hubbasics.hub.features;

import com.valkyrie.hubbasics.HubBasics;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import com.valkyrie.hubbasics.commands.essentials.FlyCommand;

public class DoubleJump implements Listener {

    @EventHandler
    public void onJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if( player.getGameMode() == GameMode.CREATIVE || (FlyCommand.flying.contains(player))) {
            return;
        }

        event.setCancelled(true);
        if(HubBasics.get().getConfig().getBoolean("DOUBLEJUMP.ENABLE"))
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().normalize().multiply(2).setY(1));
        player.playSound(player.getLocation(),Sound.valueOf((HubBasics.get().getConfig().getString("DOUBLEJUMP.SOUND"))),
                (float) HubBasics.get().getConfig().getDouble("DOUBLEJUMP.VOLUME"),
                (float) HubBasics.get().getConfig().getDouble("DOUBLEJUMP.PITCH"));
    }

    @EventHandler
    public void onPlayerGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE || (FlyCommand.flying.contains(player))) {
            return;
        }
        if(HubBasics.get().getConfig().getBoolean("DOUBLEJUMP.ENABLE"))
        if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR) {
            player.setAllowFlight(true);
        }
    }
}