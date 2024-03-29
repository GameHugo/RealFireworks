package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.utils.FireworkEffects;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.firework.FireworkBuilder;
import me.gamehugo.realfireworks.utils.firework.FireworkType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FireworkExplodeEvent;

public class OnFireworkExplode implements Listener {

    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent e) {
        if(!FireworkBuilder.getActiveEntityIds().containsKey(e.getEntity().getEntityId())) return;
        if(e.getEntity().getCustomName() == null || !e.getEntity().getCustomName().equals("RealFireworks")) return;
        FireworkEffects fireworkEffects = FireworkBuilder.getActiveEntityIds().get(e.getEntity().getEntityId()).values().iterator().next();
        FireworkInfo fireworkInfo = FireworkBuilder.getActiveEntityIds().get(e.getEntity().getEntityId()).keySet().iterator().next();
        FireworkBuilder.getActiveEntityIds().remove(e.getEntity().getEntityId());
        if(fireworkEffects != null && fireworkEffects.hasSmoke()) {
            int size = fireworkEffects.getSmokeSize();
            Location loc = e.getEntity().getLocation();
            if(fireworkInfo.getFireworkType().equals(FireworkType.ROCKET)) {
                loc.add(0, -1, 0);
            }
            e.getEntity().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc,
                    fireworkEffects.getSmokeIntensity()*5, size*0.25, -size*0.25, size*0.25, 0.005);
        }
    }
}
