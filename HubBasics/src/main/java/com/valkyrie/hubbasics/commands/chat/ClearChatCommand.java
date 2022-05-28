package com.valkyrie.hubbasics.commands.chat;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.security.Permissions;

public class ClearChatCommand implements CommandExecutor {


        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            if (sender instanceof Player){
                if (!sender.hasPermission(HubBasics.get().getPermissionsYml().getString("CHAT.clear-chat"))){
                    sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
                    return false;
                }
            }

            if (args.length == 0){

                for (int i = 0; i < 100; i++){
                    Bukkit.broadcastMessage(" ");
                    Bukkit.broadcastMessage(CC.translate("&c &f"));
                }

                Bukkit.broadcastMessage(CC.translate(HubBasics.get().getMessagesYml().getString("clear-chat.broadcast")
                        .replace("{player}", sender.getName())));

                return true;
            }

            sender.sendMessage(CC.translate("&cUsage: /clearchat"));
            return false;

        }
    }