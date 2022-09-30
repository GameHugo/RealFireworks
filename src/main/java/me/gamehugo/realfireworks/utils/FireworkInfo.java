package me.gamehugo.realfireworks.utils;

import me.gamehugo.realfireworks.utils.fireworktypes.FireworkType;

import java.util.ArrayList;
import java.util.List;

public class FireworkInfo {
    private String name;
    private List<String> lore;
    private FireworkType fireworkType;
    private boolean smoke;
    private int SmokeIntensity;
    private FireworkEffects fireworkEffects;
    private final List<CakeEffect> tubes = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public void setFireworkType(FireworkType fireworkType) {
        this.fireworkType = fireworkType;
    }
    public void setSmoke(boolean smoke) {
        this.smoke = smoke;
    }
    public void setSmokeIntensity(int smokeIntensity) {
        SmokeIntensity = smokeIntensity;
    }
    public void setFireworkEffects(FireworkEffects fireworkEffects) {
        this.fireworkEffects = fireworkEffects;
    }
    public void addTube(CakeEffect cakeEffect) {
        tubes.add(cakeEffect);
    }

    public String getName() {
        return name;
    }
    public List<String> getLore() {
        return lore;
    }
    public FireworkType getFireworkType() {
        return fireworkType;
    }
    public boolean hasSmoke() {
        return smoke;
    }
    public int getSmokeIntensity() {
        return SmokeIntensity;
    }
    public FireworkEffects getFireworkEffects() {
        return fireworkEffects;
    }
    public List<CakeEffect> getTubes() {
        return tubes;
    }
}
