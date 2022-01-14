package me.gamehugo.realfireworks.Listeners;

import me.gamehugo.realfireworks.Utils.Chat;
import me.gamehugo.realfireworks.Events.FireworkLaunchEvent;
import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.Utils.Files.Fireworks;
import me.gamehugo.realfireworks.Utils.FireworkInfo;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class OnFireworkIgnite implements Listener {

    @EventHandler
    public void OnInteract(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && Objects.requireNonNull(e.getItem()).getType().equals(Material.FIREWORK_ROCKET)) {
            for(FireworkInfo fireworkInfo : Fireworks.getFireworksList().values()) {
                if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals(Chat.color(fireworkInfo.getName()))) {
                    e.setCancelled(true);
                    FireworkLaunchEvent fireworkLaunchEvent = new FireworkLaunchEvent(fireworkInfo);
                    Bukkit.getPluginManager().callEvent(fireworkLaunchEvent);
                    if(!fireworkLaunchEvent.isCancelled()) {
                        switch (fireworkInfo.getFireworkType()) {
                            case Ground:
                                RealFireworks.ground.makeFirework(Objects.requireNonNull(e.getClickedBlock()).getLocation(), fireworkInfo);
                                break;
                            case Rocket:
                                RealFireworks.rocket.makeFirework(Objects.requireNonNull(e.getClickedBlock()).getLocation(), fireworkInfo);
                                break;
                            case Fountain:
                                RealFireworks.fountain.makeFirework();
                                break;
                            case Cake:
                                RealFireworks.cake.makeFirework(Objects.requireNonNull(e.getClickedBlock()).getLocation(), fireworkInfo);
                                break;
                        }
                        return;
                    }
                }
            }
        }
    }
}
