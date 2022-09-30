package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.utils.fireworktypes.Firework;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FireworkExplodeEvent;

public class OnFireworkExplode implements Listener {

    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent e) {
        if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().equals("RealFireworks") && Firework.getFireworkIds().get(e.getEntity().getEntityId()).hasSmoke()) {
            e.getEntity().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, e.getEntity().getLocation(),
                    Firework.getFireworkIds().get(e.getEntity().getEntityId()).getSmokeIntensity()*10, 1, -1, 1, 0.02);
        }
    }
}
