package com.valkyrie.hubbasics.commands.spawn;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-console")));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(HubBasics.get().getPermissionsYml().getString("SPAWN.setspawn"))) {
            sender.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("no-permission")));
        }

        Location location = player.getLocation();

        HubBasics.get().getConfig().set("SPAWN.LOCATION", LocationUtil.parseToString(location));
        player.sendMessage(CC.translate(HubBasics.get().getMessagesYml().getString("spawn.spawn-added")));
        player.playSound(player.getLocation(),
                Sound.valueOf(HubBasics.get().getConfig().getString("SPAWN.SEND.SOUND")),
                (float) HubBasics.get().getConfig().getDouble("SPAWN.SEND.VOLUME"),
                (float) HubBasics.get().getConfig().getDouble("SPAWN.SEND.PITCH"));

        HubBasics.get().saveConfig();
        HubBasics.get().reloadConfig();

        return false;
    }
}
