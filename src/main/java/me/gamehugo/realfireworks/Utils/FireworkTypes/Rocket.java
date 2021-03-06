package me.gamehugo.realfireworks.Utils.FireworkTypes;

import me.gamehugo.realfireworks.Utils.FireworkEffects;
import me.gamehugo.realfireworks.Utils.FireworkInfo;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Objects;

public class Rocket {

    public void makeFirework(Location loc, FireworkInfo fireworkInfo) {
        FireworkEffects fireworkEffects = fireworkInfo.getFireworkEffects();
        FireworkEffect fe = Builder.build(fireworkInfo);
        Firework f = (Firework) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.add(0.5, 1, 0.5), EntityType.FIREWORK);
        FireworkMeta fm = f.getFireworkMeta();
        fm.clearEffects();
        fm.addEffect(fe);
        fm.setPower(fireworkEffects.getPower());
        f.setFireworkMeta(fm);
    }
}
