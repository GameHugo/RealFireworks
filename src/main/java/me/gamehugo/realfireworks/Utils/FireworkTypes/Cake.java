package me.gamehugo.realfireworks.Utils.FireworkTypes;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.Utils.CakeEffect;
import me.gamehugo.realfireworks.Utils.FireworkInfo;
import org.bukkit.Bukkit;
import org.bukkit.Color;
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
                    FireworkEffect.Builder fb = FireworkEffect.builder();
                    fb.withColor(Color.fromRGB(cakeEffect.getFireworkEffects().getRed(), cakeEffect.getFireworkEffects().getGreen(), cakeEffect.getFireworkEffects().getBlue()));
                    if(cakeEffect.getFireworkEffects().getFade()) {
                        fb.withFade(Color.fromRGB(cakeEffect.getFireworkEffects().getFadeRed(), cakeEffect.getFireworkEffects().getFadeGreen(), cakeEffect.getFireworkEffects().getFadeBlue()));
                    }
                    fb.flicker(cakeEffect.getFireworkEffects().getFlicker());
                    fb.trail(cakeEffect.getFireworkEffects().getTrail());
                    FireworkEffect fe = fb.build();
                    Firework f = (Firework) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc.clone().add(0.5, 1, 0.5), EntityType.FIREWORK);
                    FireworkMeta fm = f.getFireworkMeta();
                    fm.clearEffects();
                    fm.addEffect(fe);
                    fm.setPower(cakeEffect.getFireworkEffects().getPower());
                    f.setFireworkMeta(fm);
                }, cakeEffect.getDelay());
            }
        }
    }
}
