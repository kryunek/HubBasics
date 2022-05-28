package com.valkyrie.hubbasics.commands.chat;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    public static boolean isChatMuted = false;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            if (!sender.hasPermission(HubBasics.get().getPermissionsYml().getString("CHAT.mute-chat"))){
                sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
                return false;
            }
        }

        if (!isChatMuted){
            isChatMuted = true;
            Bukkit.broadcastMessage(CC.translate(HubBasics.get().getMessagesYml().getString("mute-chat.mute.broadcast").replace("{player}", sender.getName())));
            return true;
        } else{
            isChatMuted = false;
            Bukkit.broadcastMessage(CC.translate(HubBasics.get().getMessagesYml().getString("mute-chat.unmute.broadcast").replace("{player}", sender.getName())));
            return true;
        }
    }
}
