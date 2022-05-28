package com.valkyrie.hubbasics.commands.media;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-console")));
            return false;
        }

        final Player player = (Player) sender;

        if (player.hasPermission(HubBasics.get().getPermissionsYml().getString("MEDIA.discord"))) {
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("media-links.discord").replace("%bullet_point%", "•")));
            }
            for (String list : HubBasics.get().getMessagesYml().getStringList("")) {
                player.sendMessage(CC.translate(list).replace("%bullet_point%", "✧"));
            }

        } else {
            player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
        }
        return true;
    }
}