package com.valkyrie.hubbasics.commands.essentials;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.hub.features.Ping;
import com.valkyrie.hubbasics.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {


    public static ArrayList<Player> flying = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-console")));
        }

        if (!sender.hasPermission(HubBasics.get().getPermissionsYml().getString("ESSENTIALS.fly"))) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
        }

        if(sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission(HubBasics.get().getPermissionsYml().getString("ESSENTIALS.fly"))) {
                if (flying.contains(player)) {
                    flying.remove(player);
                    player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("fly.fly-disabled")));
                    player.setAllowFlight(false);
                } else if (!flying.contains(player)) {
                    flying.add(player);
                    player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("fly.fly-enabled")));
                    player.setAllowFlight(true);
                }
            }
        }

        return true;
    }
}
