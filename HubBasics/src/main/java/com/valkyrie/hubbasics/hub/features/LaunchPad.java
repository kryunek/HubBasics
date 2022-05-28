package com.valkyrie.hubbasics.hub.features;

import com.valkyrie.hubbasics.HubBasics;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LaunchPad implements Listener {

    @EventHandler
    public void onLaunchEvent(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if(HubBasics.get().getConfig().getBoolean("LAUNCH_PAD.ENABLE"))
        if (player.getLocation().getBlock().getType() == Material.valueOf(HubBasics.get().getConfig().getString("LAUNCH_PAD.MATERIAL"))) {
            Vector vectah = player.getLocation().getDirection().multiply(2.0).setY(1.0);
            player.setVelocity(vectah);

            player.playSound(player.getLocation(), Sound.valueOf(HubBasics.get().getConfig().getString("LAUNCH_PAD.SOUND")),
            (float) HubBasics.get().getConfig().getDouble("LAUNCH_PAD.VOLUME"),
            (float) HubBasics.get().getConfig().getDouble("LAUNCH_PAD.PITCH"));
        }
    }
}