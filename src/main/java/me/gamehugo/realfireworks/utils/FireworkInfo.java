package me.gamehugo.realfireworks.utils;

import me.gamehugo.realfireworks.utils.firework.FireworkType;

import java.util.ArrayList;
import java.util.List;

public class FireworkInfo {
    private String id;
    private String name;
    private List<String> lore;
    private FireworkType fireworkType;
    private FireworkEffects fireworkEffects;
    private final List<CakeEffect> tubes = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLore(List<String> lore) {
        this.lore = lore;
    }
    public void setFireworkType(FireworkType fireworkType) {
        this.fireworkType = fireworkType;
    }
    public void setFireworkEffects(FireworkEffects fireworkEffects) {
        this.fireworkEffects = fireworkEffects;
    }
    public void addTube(CakeEffect cakeEffect) {
        tubes.add(cakeEffect);
    }

    public String getId() {
        return id;
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
    public FireworkEffects getFireworkEffects() {
        return fireworkEffects;
    }
    public List<CakeEffect> getTubes() {
        return tubes;
    }
}
