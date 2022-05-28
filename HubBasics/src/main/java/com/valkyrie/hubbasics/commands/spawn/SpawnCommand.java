package com.valkyrie.hubbasics.commands.spawn;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-console")));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(HubBasics.get().getPermissionsYml().getString("SPAWN.spawn"))) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
        }

        if(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")) != null){
            player.teleport(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")));

        } else {
            Bukkit.getConsoleSender().sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("spawn.no-spawn")));
        }

        player.teleport(LocationUtil.parseToLocation(HubBasics.get().getConfig().getString("SPAWN.LOCATION")));
        player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("spawn.message")));
        player.playSound(player.getLocation(),
                Sound.valueOf(HubBasics.get().getConfig().getString("SPAWN.SEND.SOUND")),
                (float) HubBasics.get().getConfig().getDouble("SPAWN.SEND.VOLUME"),
                (float) HubBasics.get().getConfig().getDouble("SPAWN.SEND.PITCH"));

        return false;
    }
}
