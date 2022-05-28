package com.valkyrie.hubbasics.commands.essentials;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.hub.features.Ping;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cThis command cannot be executed from console"));
            return false;
        }
        assert sender instanceof Player;
        final Player player = (Player) sender;


        if (!player.hasPermission(HubBasics.get().getPermissionsYml().getString("ESSENTIALS.ping"))) {
            player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
        } else if (player.hasPermission(HubBasics.get().getPermissionsYml().getString("ESSENTIALS.ping"))) {
            player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("ping.ping").replace("{ping}", String.valueOf(Ping.getPing(player)))));
        }
        return false;
    }
}
