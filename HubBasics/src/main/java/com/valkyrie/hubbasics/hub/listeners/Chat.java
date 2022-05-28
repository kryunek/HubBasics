package com.valkyrie.hubbasics.hub.listeners;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.commands.chat.MuteChatCommand;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if (!MuteChatCommand.isChatMuted){
            return;
        }

        if (player.hasPermission(HubBasics.get().getPermissionsYml().getString("CHAT.mute-chat-bypass"))){
            return;
        }

        e.setCancelled(true);
        player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("mute-chat.mute.chat-fail")));
    }

}