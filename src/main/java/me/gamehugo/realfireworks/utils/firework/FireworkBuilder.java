package me.gamehugo.realfireworks.utils.firework;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.CakeEffect;
import me.gamehugo.realfireworks.utils.FireworkEffects;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FireworkBuilder {
    private static final Map<Integer, Map<FireworkInfo, FireworkEffects>> activeEntityIds = new HashMap<>();

    public static void igniteFirework(Location location, FireworkInfo fireworkInfo) {
        if(fireworkInfo.getFireworkType().equals(FireworkType.GROUND)) {
            ground(location, fireworkInfo);
        } else if(fireworkInfo.getFireworkType().equals(FireworkType.ROCKET)) {
            rocket(location, fireworkInfo);
        } else if(fireworkInfo.getFireworkType().equals(FireworkType.CAKE)) {
            cake(location, fireworkInfo);
        } else if(fireworkInfo.getFireworkType().equals(FireworkType.FOUNTAIN)) {
            fountain(location, fireworkInfo);
        } else {
            RealFireworks.getInstance().getLogger().warning("FireworkType of '"+fireworkInfo.getName()+"' is not valid!");
        }
    }

    private static void ground(Location location, FireworkInfo fireworkInfo) {
        if(fireworkInfo.getFireworkType() != FireworkType.GROUND) return;
        Firework firework = (Firework)Objects.requireNonNull(location.getWorld()).spawnEntity(location.add(0.5, 1, 0.5), EntityType.FIREWORK);
        setFireworkInfo(firework, fireworkInfo, fireworkInfo.getFireworkEffects());
        firework.detonate();
    }

    private static void rocket(Location location, FireworkInfo fireworkInfo) {
        if(fireworkInfo.getFireworkType() != FireworkType.ROCKET) return;
        Firework firework = (Firework) Objects.requireNonNull(location.getWorld()).spawnEntity(location.add(0.5, 1, 0.5), EntityType.FIREWORK);
        setFireworkInfo(firework, fireworkInfo, fireworkInfo.getFireworkEffects());
    }

    private static void fountain(Location location, FireworkInfo fireworkInfo) {
        if(fireworkInfo.getFireworkType() != FireworkType.FOUNTAIN) return;
        RealFireworks.getInstance().getLogger().severe("Fountains are not implemented yet!");
        RealFireworks.getInstance().getLogger().severe("Info > " +
                "Name: "+fireworkInfo.getName()+" | " +
                "Type: "+fireworkInfo.getFireworkType().toString()+" | " +
                "Location: "+location.toString());
    }

    private static void cake(Location location, FireworkInfo fireworkInfo) {
        if(fireworkInfo.getFireworkType() != FireworkType.CAKE) return;
        if(fireworkInfo.getTubes().size() > 0) {
            for (CakeEffect cakeEffect : fireworkInfo.getTubes()) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(RealFireworks.getInstance(), () -> {
                    Firework firework = (Firework) Objects.requireNonNull(location.getWorld()).spawnEntity(location.clone().add(0.5, 1, 0.5), EntityType.FIREWORK);
                    setFireworkInfo(firework, fireworkInfo, cakeEffect.getFireworkEffects());
                }, cakeEffect.getDelay());
            }
        } else {
            RealFireworks.getInstance().getLogger().severe("Failed to make firework "+fireworkInfo.getName()+" no tubes in CakeEffect");
        }
    }

    public static void setFireworkInfo(Firework firework, FireworkInfo fireworkInfo, FireworkEffects fireworkEffects) {
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.clearEffects();
        FireworkEffect fireworkEffect = buildEffects(fireworkEffects);
        fireworkMeta.addEffect(fireworkEffect);
        fireworkMeta.setPower(fireworkEffects.getPower());
        firework.setFireworkMeta(fireworkMeta);
        firework.setCustomName("RealFireworks");
        Map<FireworkInfo, FireworkEffects> fireworkEffectsMap = activeEntityIds.getOrDefault(firework.getEntityId(), new HashMap<>());
        fireworkEffectsMap.put(fireworkInfo, fireworkEffects);
        activeEntityIds.put(firework.getEntityId(), fireworkEffectsMap);
    }

    public static FireworkEffect buildEffects(FireworkEffects fireworkEffects) {
        FireworkEffect.Builder fb = FireworkEffect.builder();
        fb.with(fireworkEffects.getType());
        fb.withColor(Color.fromRGB(fireworkEffects.getRed(), fireworkEffects.getGreen(), fireworkEffects.getBlue()));
        if(fireworkEffects.getFade()) {
            fb.withFade(Color.fromRGB(fireworkEffects.getFadeRed(), fireworkEffects.getFadeGreen(), fireworkEffects.getFadeBlue()));
        }
        fb.flicker(fireworkEffects.getFlicker());
        fb.trail(fireworkEffects.getTrail());
        return fb.build();
    }

    public static Map<Integer, Map<FireworkInfo, FireworkEffects>> getActiveEntityIds() {
        return activeEntityIds;
    }
}
