package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.fireworktypes.Firework;
import me.gamehugo.realfireworks.utils.fireworktypes.FireworkType;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FireworkExplodeEvent;

public class OnFireworkExplode implements Listener {

    @EventHandler
    public void onFireworkExplode(FireworkExplodeEvent e) {
        if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().equals("RealFireworks") && Firework.getFireworkIds().get(e.getEntity().getEntityId()).getFireworkEffects().hasSmoke()) {
            FireworkInfo fireworkInfo = Firework.getFireworkIds().get(e.getEntity().getEntityId());
            int size = fireworkInfo.getFireworkEffects().getSmokeSize();
            Location loc = e.getEntity().getLocation();
            if(fireworkInfo.getFireworkType().equals(FireworkType.Rocket)) {
                loc.add(0, -1, 0);
            }
            e.getEntity().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc,
                    fireworkInfo.getFireworkEffects().getSmokeIntensity()*5, size*0.25, -size*0.25, size*0.25, 0.005);
        }
    }
}
