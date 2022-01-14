package me.gamehugo.realfireworks.events;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.files.Config;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@SuppressWarnings("unused")
public class FireworkLaunchEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final FireworkInfo fireworkInfo;
    private boolean isCancelled;

    public FireworkLaunchEvent(FireworkInfo fireworkInfo) {
        this.fireworkInfo = fireworkInfo;
        this.isCancelled = false;
        if(Config.getConfig().getBoolean("Debug")) RealFireworks.getInstance().getLogger().info("FireworkLaunchEvent triggered.\n" +
                    "Name: "+fireworkInfo.getName()+"\n"+
                    "Type: "+fireworkInfo.getFireworkType()+"\n" +
                    "Tubes: "+fireworkInfo.getTubes().size());
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
