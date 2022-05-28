package com.valkyrie.hubbasics.hub.listeners;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.commands.chat.SlowChatCommand;
import com.valkyrie.hubbasics.utils.CC;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class SlowChat implements Listener {


    private static HashMap<Player, Long> chatSlow = new HashMap<>();


    @EventHandler(priority = EventPriority.HIGH)
    public void onSlowChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        if (!SlowChatCommand.isChatSlowed){
            return;
        }

        if (player.hasPermission(HubBasics.get().getPermissionsYml().getString("CHAT.slow-chat-bypass"))){
            return;
        }

        if (chatSlow.containsKey(player)){
            int slowSeconds = SlowChatCommand.slowSeconds;
            long timeRemaining = chatSlow.get(player) + slowSeconds * 1000L - System.currentTimeMillis();
            if (timeRemaining > 0L){
                e.setCancelled(true);
                int waitSeconds = Integer.parseInt(DurationFormatUtils.formatDuration(timeRemaining, "s"));
                player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("slow-chat.enable.chat-fail")
                        .replace("{seconds}", waitSeconds + "")));
                return;
            }
            chatSlow.remove(player);
        }

        chatSlow.put(player, System.currentTimeMillis());
    }

}