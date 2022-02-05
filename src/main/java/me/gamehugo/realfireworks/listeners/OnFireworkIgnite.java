package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.utils.Chat;
import me.gamehugo.realfireworks.events.FireworkLaunchEvent;
import me.gamehugo.realfireworks.utils.files.Fireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.fireworktypes.Firework;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class OnFireworkIgnite implements Listener {

    @EventHandler
    public void OnInteract(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().getType().equals(Material.FIREWORK_ROCKET)) {
            for(FireworkInfo fireworkInfo : Fireworks.getFireworksList().values()) {
                if(Objects.requireNonNull(e.getItem().getItemMeta()).getDisplayName().equals(Chat.color(fireworkInfo.getName()))) {
                    e.setCancelled(true);
                    FireworkLaunchEvent fireworkLaunchEvent = new FireworkLaunchEvent(fireworkInfo);
                    Bukkit.getPluginManager().callEvent(fireworkLaunchEvent);
                    if(!fireworkLaunchEvent.isCancelled()) {
                        if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL) || e.getPlayer().getGameMode().equals(GameMode.ADVENTURE))
                            e.getItem().setAmount(e.getItem().getAmount()-1);
                        switch (fireworkInfo.getFireworkType()) {
                            case Ground:
                                Firework.getGround().makeFirework(Objects.requireNonNull(e.getClickedBlock()).getLocation(), fireworkInfo);
                                break;
                            case Rocket:
                                Firework.getRocket().makeFirework(Objects.requireNonNull(e.getClickedBlock()).getLocation(), fireworkInfo);
                                break;
                            case Fountain:
                                Firework.getFountain().makeFirework();
                                break;
                            case Cake:
                                Firework.getCake().makeFirework(Objects.requireNonNull(e.getClickedBlock()).getLocation(), fireworkInfo);
                                break;
                        }
                        return;
                    }
                }
            }
        }
    }
}
