package me.gamehugo.realfireworks.utils.fireworktypes;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.files.Config;

import java.util.HashMap;
import java.util.Map;

public class Firework {
    private static final Map<Integer, FireworkInfo> fireworkIds = new HashMap<>();
    private static Ground ground;
    private static Rocket rocket;
    private static Fountain fountain;
    private static Cake cake;

    public void setup() {
        ground = new Ground();
        rocket = new Rocket();
        fountain = new Fountain();
        cake = new Cake();
        if(Config.getConfig().getBoolean("Debug")) RealFireworks.getInstance().getLogger().info("Loaded firework types");
    }

    public static Ground getGround() {
        return ground;
    }
    public static Rocket getRocket() {
        return rocket;
    }
    public static Fountain getFountain() {
        return fountain;
    }
    public static Cake getCake() {
        return cake;
    }
    public static Map<Integer, FireworkInfo> getFireworkIds() {
        return fireworkIds;
    }
}
