package com.valkyrie.hubbasics.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.valkyrie.hubbasics.HubBasics;
import org.bukkit.entity.Player;

public class BungeeUtil {

    public static void sendToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(HubBasics.get(), "BungeeCord", out.toByteArray());
    }
}