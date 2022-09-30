package me.gamehugo.realfireworks.utils.fireworktypes;

import me.gamehugo.realfireworks.utils.FireworkEffects;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Objects;

public class Ground {

    public void makeFirework(Location loc, FireworkInfo fireworkInfo) {
        FireworkEffects fireworkEffects = fireworkInfo.getFireworkEffects();
        FireworkEffect fe = Builder.build(fireworkEffects);
        Firework f = (Firework) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.add(0.5, 1, 0.5), EntityType.FIREWORK);
        FireworkMeta fm = f.getFireworkMeta();
        fm.clearEffects();
        fm.addEffect(fe);
        fm.setPower(fireworkEffects.getPower());
        f.setFireworkMeta(fm);
        f.setCustomName("RealFireworks");
        me.gamehugo.realfireworks.utils.fireworktypes.Firework.getFireworkIds().put(f.getEntityId(), fireworkInfo);
        f.detonate();
    }

}
