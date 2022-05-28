package com.valkyrie.hubbasics.hub.features;

import com.valkyrie.hubbasics.HubBasics;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class AlwaysDay implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void onWeatherChangeEvent(WeatherChangeEvent event)
    {
        event.setCancelled(true);
        if (HubBasics.get().getConfig().getBoolean("ALWAYS_SUNNY.ENABLE"))
        event.getWorld().setWeatherDuration(0);
        event.getWorld().setTime(3000);
    }

}
