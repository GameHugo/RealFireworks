package me.gamehugo.realfireworks.Utils.FireworkTypes;

import me.gamehugo.realfireworks.Utils.FireworkEffects;
import me.gamehugo.realfireworks.Utils.FireworkInfo;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;

public class Builder {

    public static FireworkEffect build(FireworkInfo fireworkInfo) {
        FireworkEffect.Builder fb = FireworkEffect.builder();
        FireworkEffects fireworkEffects = fireworkInfo.getFireworkEffects();
        fb.withColor(Color.fromRGB(fireworkEffects.getRed(), fireworkEffects.getGreen(), fireworkEffects.getBlue()));
        if(fireworkEffects.getFade()) {
            fb.withFade(Color.fromRGB(fireworkEffects.getFadeRed(), fireworkEffects.getFadeGreen(), fireworkEffects.getFadeBlue()));
        }
        fb.flicker(fireworkEffects.getFlicker());
        fb.trail(fireworkEffects.getTrail());
        return fb.build();
    }
}
