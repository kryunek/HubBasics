package com.valkyrie.hubbasics.commands.chat;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlowChatCommand implements CommandExecutor {

    public static boolean isChatSlowed = false;
    public static int slowSeconds;


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player){
            if (!sender.hasPermission(HubBasics.get().getPermissionsYml().getString("CHAT.slow-chat"))){
                sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
                return false;
            }
        }

        if (args.length == 0){
            if (isChatSlowed){
                isChatSlowed = false;
                Bukkit.broadcastMessage(CC.translate(HubBasics.get().getMessagesYml().getString("slow-chat.disable.broadcast")
                        .replace("{player}", sender.getName())));
                return true;
            } else{
                sender.sendMessage(CC.translate("&cUsage: /slowchat <seconds>"));
                return false;
            }

        }

        if (args.length == 1){
            try{
                slowSeconds = Integer.parseInt(args[0]);
            } catch(IllegalArgumentException e){
                sender.sendMessage(CC.translate("&cPlease supply a valid number."));
                return false;
            }


            if (!isChatSlowed){
                isChatSlowed = true;
                Bukkit.broadcastMessage(CC.translate(HubBasics.get().getMessagesYml().getString("slow-chat.enable.broadcast")
                        .replace("{player}", sender.getName())
                        .replace("{seconds}", args[0])));
                return true;
            } else{
                isChatSlowed = false;
                Bukkit.broadcastMessage(CC.translate(HubBasics.get().getMessagesYml().getString("slow-chat.disable.broadcast")
                        .replace("{player}", sender.getName())));

                return true;
            }


        }

        sender.sendMessage(CC.translate("&cUsage: /slowchat <Seconds>"));
        return false;

    }
}
