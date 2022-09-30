package me.gamehugo.realfireworks.utils.fireworktypes;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.CakeEffect;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import org.bukkit.Bukkit;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Objects;

public class Cake {

    public void makeFirework(Location loc, FireworkInfo fireworkInfo) {
        if(fireworkInfo.getTubes().size() > 0) {
            for (CakeEffect cakeEffect : fireworkInfo.getTubes()) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(RealFireworks.getInstance(), () -> {
                    FireworkEffect fe = Builder.build(cakeEffect.getFireworkEffects());
                    Firework f = (Firework) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.clone().add(0.5, 1, 0.5), EntityType.FIREWORK);
                    FireworkMeta fm = f.getFireworkMeta();
                    fm.clearEffects();
                    fm.addEffect(fe);
                    fm.setPower(cakeEffect.getFireworkEffects().getPower());
                    f.setFireworkMeta(fm);
                    f.setCustomName("RealFireworks");
                }, cakeEffect.getDelay());
            }
        } else {
            RealFireworks.getInstance().getLogger().severe("Failed to make firework "+fireworkInfo.getName()+" no tubes in CakeEffect");
        }
    }
}
