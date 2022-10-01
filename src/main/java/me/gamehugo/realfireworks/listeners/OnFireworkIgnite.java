package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.events.FireworkLaunchEvent;
import me.gamehugo.realfireworks.utils.files.Messages;
import me.gamehugo.realfireworks.utils.firework.Fireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.firework.FireworkBuilder;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Map;
import java.util.Objects;

public class OnFireworkIgnite implements Listener {

    @EventHandler
    public void OnInteract(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().getType().equals(Material.FIREWORK_ROCKET)) {
            if(e.getClickedBlock() != null && e.getClickedBlock().getType().name().contains("DOOR")) return;
            for(Map<String, FireworkInfo> fireworkInfoMap : Fireworks.getFireworksList().values()) {
                for(FireworkInfo fireworkInfo : fireworkInfoMap.values()) {
                    if (Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals(Messages.color(fireworkInfo.getName()))) {
                        e.setCancelled(true);
                        FireworkLaunchEvent fireworkLaunchEvent = new FireworkLaunchEvent(fireworkInfo);
                        Bukkit.getPluginManager().callEvent(fireworkLaunchEvent);
                        if (!fireworkLaunchEvent.isCancelled()) {
                            if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL) || e.getPlayer().getGameMode().equals(GameMode.ADVENTURE))
                                e.getItem().setAmount(e.getItem().getAmount() - 1);
                            FireworkBuilder.igniteFirework(e.getClickedBlock().getLocation(), fireworkInfo);
                            return;
                        }
                    }
                }
            }
        }
    }
}
