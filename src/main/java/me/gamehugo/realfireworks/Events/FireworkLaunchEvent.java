package me.gamehugo.realfireworks.Events;

import me.gamehugo.realfireworks.Utils.FireworkInfo;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FireworkLaunchEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final FireworkInfo fireworkInfo;
    private boolean isCancelled;

    public FireworkLaunchEvent(FireworkInfo fireworkInfo) {
        this.fireworkInfo = fireworkInfo;
        this.isCancelled = false;
    }

    public FireworkInfo getFireworkInfo() {
        return fireworkInfo;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
