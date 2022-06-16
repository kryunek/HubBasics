package com.valkyrie.hubbasics.captcha.listener;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.captcha.menu.CaptchaMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (HubBasics.get().getConfig().getBoolean("CAPTCHA.ENABLED")) {
            if (HubBasics.get().getConfig().getBoolean("CAPTCHA.BYPASS")) {
                if (!event.getPlayer().hasPermission(HubBasics.get().getConfig().getString("CAPTCHA.BYPASS_PERMISSION")))
                    CaptchaMenu.openCaptcha(event.getPlayer());
            } else
                CaptchaMenu.openCaptcha(event.getPlayer());
        }
    }
}