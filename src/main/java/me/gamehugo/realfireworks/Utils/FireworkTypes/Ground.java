package me.gamehugo.realfireworks.Utils.FireworkTypes;

import me.gamehugo.realfireworks.Utils.FireworkEffects;
import me.gamehugo.realfireworks.Utils.FireworkInfo;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Objects;

public class Ground {

    public void makeFirework(Location loc, FireworkInfo fireworkInfo) {
        FireworkEffect fe = Builder.build(fireworkInfo);
        FireworkEffects fireworkEffects = fireworkInfo.getFireworkEffects();
        Firework f = (Firework) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.add(0.5, 0, 0.5), EntityType.FIREWORK);
        FireworkMeta fm = f.getFireworkMeta();
        fm.clearEffects();
        fm.addEffect(fe);
        fm.setPower(fireworkEffects.getPower());
        f.setFireworkMeta(fm);
        f.detonate();
    }

}
