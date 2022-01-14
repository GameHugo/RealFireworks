package me.gamehugo.realfireworks.Utils.Files;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.Utils.CakeEffect;
import me.gamehugo.realfireworks.Utils.Console;
import me.gamehugo.realfireworks.Utils.FireworkEffects;
import me.gamehugo.realfireworks.Utils.FireworkInfo;
import me.gamehugo.realfireworks.Utils.FireworkTypes.FireworkType;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Fireworks {

    private static FileConfiguration fileConfig;
    private static HashMap<String, FireworkInfo> fireworksList;

    public static void setupFile() {
        File file = new File(RealFireworks.getInstance().getDataFolder(), "fireworks.yml");

        if (!file.exists()) {
            RealFireworks.getInstance().saveResource("fireworks.yml", false);
        }

        fileConfig = new YamlConfiguration();

        try {
            fileConfig.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void loadFireworks() {
        fireworksList = new HashMap<>();
        for(String name : fileConfig.getKeys(false)) {
            FireworkInfo fireworkInfo = getFireworkInfo(name);
            if(!fireworksList.containsKey(name)) {
                boolean exists = false;
                for(FireworkInfo fireworkInfos : fireworksList.values()) {
                    assert fireworkInfo != null;
                    if(fireworkInfos.getName().equals(fireworkInfo.getName())) {
                        Console.sendMessage("&c"+name+" has a double displayname in config.");
                        exists = true;
                        break;
                    }
                }
                if(!exists) {
                    fireworksList.put(name, fireworkInfo);
                }
            } else {
                Console.sendMessage("&c"+name+" is double in config.");
            }
        }
    }

    public static FireworkInfo getFireworkInfo(String path) {
        FireworkInfo fireworkInfo = new FireworkInfo();
        if(fileConfig.getString(path+".Name") == null) {
            Console.sendMessage("&cFailed to load a firework...\n&cPATH: "+path);
            return null;
        }
        fireworkInfo.setName(fileConfig.getString(path+".Name"));
        fireworkInfo.setLore(fileConfig.getStringList(path+".Lore"));
        if(FireworkType.getType(Objects.requireNonNull(fileConfig.getString(path+".FireworkType"))) == null) {
            Console.sendMessage("&cFailed to load firework "+fileConfig.getString(path+".Name")+" invalid FireworkType\n&cPATH: "+path);
            return null;
        }
        fireworkInfo.setFireworkType(FireworkType.getType(Objects.requireNonNull(fileConfig.getString(path+".FireworkType"))));
        if(fireworkInfo.getFireworkType().equals(FireworkType.Cake)) {
            for(String id : Objects.requireNonNull(fileConfig.getConfigurationSection(path+".CakeEffects")).getKeys(false)) {
                CakeEffect cakeEffect = new CakeEffect();
                cakeEffect.setDelay(fileConfig.getInt(path+".CakeEffects."+id+".Delay"));
                cakeEffect.setFireworkEffects(convertFireworkEffects(path+".CakeEffects."+id));
                fireworkInfo.addTube(cakeEffect);
            }
        } else {
            fireworkInfo.setFireworkEffects(convertFireworkEffects(path));
        }
        return fireworkInfo;
    }

    public static FireworkEffects convertFireworkEffects(String path) {
        FireworkEffects fireworkEffects = new FireworkEffects();
        if (fileConfig.get(path + ".FireworkEffects") == null) {
            Console.sendMessage("&cFailed to load firework " + fileConfig.getString(path + ".Name") + " no FireworkEffects\n&cPATH: " + path);
            return null;
        }
        if (fileConfig.get(path + ".FireworkEffects.Power") == null) {
            Console.sendMessage("&cFailed to load firework " + fileConfig.getString(path + ".Name") + " no power in FireworkEffects\n&cPATH: " + path);
            return null;
        }
        fireworkEffects.setPower(fileConfig.getInt(path + ".FireworkEffects.Power"));
        fireworkEffects.setColor(
                fileConfig.getInt(path + ".FireworkEffects.Red"),
                fileConfig.getInt(path + ".FireworkEffects.Green"),
                fileConfig.getInt(path + ".FireworkEffects.Blue"));
        fireworkEffects.setFade(fileConfig.getBoolean(path + ".FireworkEffects.Fade"));
        if (fireworkEffects.getFade()) {
            if (fileConfig.get(path + ".FireworkEffects.FadeRed") == null || fileConfig.get(path + ".FireworkEffects.FadeGreen") == null || fileConfig.get(path + ".FireworkEffects.FadeBlue") == null) {
                Console.sendWarningMessage("You are missing fade colors with firework " + fileConfig.getString(path + ".Name") + "&e so the fade will be partly black");
            }
            fireworkEffects.setFadeColor(
                    fileConfig.getInt(path + ".FireworkEffects.FadeRed"),
                    fileConfig.getInt(path + ".FireworkEffects.FadeGreen"),
                    fileConfig.getInt(path + ".FireworkEffects.FadeBlue"));
        }

        fireworkEffects.setFlicker(fileConfig.getBoolean(path + ".FireworkEffects.Flicker"));
        fireworkEffects.setTrail(fileConfig.getBoolean(path + ".FireworkEffects.Trail"));
        return fireworkEffects;
    }

    public static HashMap<String, FireworkInfo> getFireworksList() {
        return fireworksList;
    }

}
