package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.RealFireworks;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnFireworkDamage implements Listener {

    @EventHandler
    public void onFireworkDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Firework &&
           e.getDamager().getCustomName() != null &&
           e.getDamager().getCustomName().equals("RealFireworks") &&
           !RealFireworks.getInstance().getConfig().getBoolean("FireworkDamage")) {
            e.setCancelled(true);
        }
    }
}
