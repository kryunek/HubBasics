package com.valkyrie.hubbasics.scoreboard.event;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class AssembleBoardCreateEvent extends Event implements Cancellable {

    @Getter
    public static HandlerList handlerList = new HandlerList();

    private Player player;
    private boolean cancelled = false;

    public AssembleBoardCreateEvent(Player player) {
        this.player = player;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }
}