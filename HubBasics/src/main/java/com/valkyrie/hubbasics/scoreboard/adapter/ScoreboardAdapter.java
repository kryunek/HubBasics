package com.valkyrie.hubbasics.scoreboard.adapter;

import com.valkyrie.hubbasics.HubBasics;
import com.valkyrie.hubbasics.hub.features.Ping;
import com.valkyrie.hubbasics.scoreboard.AssembleAdapter;
import com.valkyrie.hubbasics.utils.CC;
import com.valkyrie.hubbasics.utils.PlayerCountThread;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
public class ScoreboardAdapter implements AssembleAdapter {

    @Override
    public String getTitle(Player player) {
        return CC.translate(HubBasics.get().getConfig().getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> list = new ArrayList<String>();

        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            for(String lines : HubBasics.get().getConfig().getStringList("SCOREBOARD.LINES").stream()

                    .map(a -> a.replace("{online}", String.valueOf(PlayerCountThread.PLAYER_COUNT)))
                    .map(a -> a.replace("{ping}", String.valueOf(Ping.getPing(player))))

                    .collect(Collectors.toList())) {

                list.add(PlaceholderAPI.setPlaceholders(player, lines));
            }
        } else {

            list.addAll(HubBasics.get().getConfig().getStringList("SCOREBOARD.LINES").stream()

                    .map(a -> a.replace("{online}", String.valueOf(PlayerCountThread.PLAYER_COUNT)))
                    .map(a -> a.replace("{ping}", String.valueOf(Ping.getPing(player))))

                    .collect(Collectors.toList()));

        }

        return list;
    }
}
