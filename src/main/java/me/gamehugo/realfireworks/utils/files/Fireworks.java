package me.gamehugo.realfireworks.utils.files;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.CakeEffect;
import me.gamehugo.realfireworks.utils.FireworkEffects;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.fireworktypes.FireworkType;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
public class Fireworks {

    private static File folder;
    private static final Map<File, Map<String, FireworkInfo>> fireworksList = new HashMap<>();
    private static final Map<File, Integer> warnings = new HashMap<>();
    public static void setup() {
        folder = new File(RealFireworks.getInstance().getDataFolder()+"/Fireworks");
    }

    public static void loadFireworks() {
        fireworksList.clear();
        warnings.clear();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if(!file.getName().substring(file.getName().lastIndexOf('.')+1).equals("yml")) {
                RealFireworks.getInstance().getLogger().severe("Wrong file type " + file.getName());
                continue;
            }
            FileConfiguration config = FileCreator.createConfig(file);
            for(String name : config.getKeys(false)) {
                FireworkInfo fireworkInfo = getFireworkInfo(file, config, name);
                if(!fireworksList.containsKey(file)) {
                    fireworksList.put(file, new HashMap<>());
                }
                if(!fireworksList.get(file).containsKey(name)) {
                    boolean exists = false;
                    for(FireworkInfo fireworkInfos : fireworksList.get(file).values()) {
                        assert fireworkInfo != null;
                        if(fireworkInfos.getName().equals(fireworkInfo.getName())) {
                            RealFireworks.getInstance().getLogger().severe(name+" has a double displayname in "+file.getName());
                            exists = true;
                            break;
                        }
                    }
                    if(!exists) {
                        assert fireworkInfo != null;
                        Map<String, FireworkInfo> map = fireworksList.get(file);
                        map.put(name, fireworkInfo);
                        fireworksList.put(file, map);
                        if(Config.getConfig().getBoolean("Debug")) {
                            RealFireworks.getInstance().getLogger().info("Loaded firework "+fireworkInfo.getName());
                        }
                    }
                } else {
                    RealFireworks.getInstance().getLogger().severe(name+" is double in "+file.getName());
                }
            }
            if(Config.getConfig().getBoolean("Debug")) {
                RealFireworks.getInstance().getLogger().info("Loaded fireworks file "+file.getName());
            }
        }

    }

    public static FireworkInfo getFireworkInfo(File file, FileConfiguration fileConfig, String path) {
        FireworkInfo fireworkInfo = new FireworkInfo();
        if(fileConfig.getString(path+".Name") == null) {
            RealFireworks.getInstance().getLogger().severe("Failed to load a firework...\nPATH: "+path);
            warnings.put(file, warnings.getOrDefault(file, 0)+1);
            return null;
        }
        fireworkInfo.setName(fileConfig.getString(path+".Name"));
        fireworkInfo.setLore(fileConfig.getStringList(path+".Lore"));
        if(FireworkType.getType(Objects.requireNonNull(fileConfig.getString(path+".FireworkType"))) == null) {
            RealFireworks.getInstance().getLogger().severe("Failed to load firework "+fileConfig.getString(path+".Name")+" invalid FireworkType\nPATH: "+path);
            warnings.put(file, warnings.getOrDefault(file, 0)+1);
            return null;
        }
        fireworkInfo.setFireworkType(FireworkType.getType(Objects.requireNonNull(fileConfig.getString(path+".FireworkType"))));
        if(fireworkInfo.getFireworkType().equals(FireworkType.Cake) && fileConfig.get(path+".CakeEffects") == null) {
            RealFireworks.getInstance().getLogger().severe("Failed to load firework "+fileConfig.getString(path+".Name")+" no CakeEffects\nPATH: "+path);
            warnings.put(file, warnings.getOrDefault(file, 0)+1);
            return null;
        }
        if(fireworkInfo.getFireworkType().equals(FireworkType.Cake)) {
            for(String id : Objects.requireNonNull(fileConfig.getConfigurationSection(path+".CakeEffects")).getKeys(false)) {
                CakeEffect cakeEffect = new CakeEffect();
                cakeEffect.setDelay(fileConfig.getInt(path+".CakeEffects."+id+".Delay"));
                cakeEffect.setFireworkEffects(convertFireworkEffects(file, fileConfig, path+".CakeEffects."+id));
                fireworkInfo.addTube(cakeEffect);
            }
        } else {
            if(fileConfig.get(path+".CakeEffects") != null) {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework "+fileConfig.getString(path+".Name")+" there is a CakeEffect and isn't a cake\nPATH: "+path);
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
                return null;
            }
            fireworkInfo.setFireworkEffects(convertFireworkEffects(file, fileConfig, path));
        }
        return fireworkInfo;
    }

    public static FireworkEffects convertFireworkEffects(File file, FileConfiguration fileConfig, String path) {
        FireworkEffects fireworkEffects = new FireworkEffects();
        String id;String tubeId = null;
        if(path.contains("CakeEffects")) {
            tubeId = path.substring(path.lastIndexOf('.') + 1);
            id = path.substring(0, path.indexOf('.', 1));
        } else {
            id = fileConfig.getString(path + ".Name");
        }
        if (fileConfig.get(path + ".FireworkEffects") == null) {
            if(tubeId == null) {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework '" + id + "' no FireworkEffects");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            } else {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework '" + id + "' and tubeID '" + tubeId + "' no FireworkEffects");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
            return null;
        }
        if (fileConfig.get(path + ".FireworkEffects.Power") == null) {
            if(tubeId == null) {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework '" + id + "' no power in FireworkEffects");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            } else {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework '" + id + "' and tubeID '" + tubeId + "' no power in FireworkEffects");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
            return null;
        }
        if(fileConfig.get(path+".FireworkEffects.Smoke") == null) {
            RealFireworks.getInstance().getLogger().warning("You are missing the Smoke with "+path);
            warnings.put(file, warnings.getOrDefault(file, 0)+1);
        }
        fireworkEffects.setSmoke(fileConfig.getBoolean(path+".FireworkEffects.Smoke"));
        if(fileConfig.get(path+".FireworkEffects.SmokeIntensity") == null) {
            RealFireworks.getInstance().getLogger().warning("You are missing the SmokeIntensity with "+path);
            warnings.put(file, warnings.getOrDefault(file, 0)+1);
            fireworkEffects.setSmokeIntensity(1);
        } else {
            fireworkEffects.setSmokeIntensity(fileConfig.getInt(path+".FireworkEffects.SmokeIntensity"));
        }
        if(fileConfig.get(path+".FireworkEffects.SmokeSize") == null) {
            RealFireworks.getInstance().getLogger().warning("You are missing the SmokeSize with "+path);
            warnings.put(file, warnings.getOrDefault(file, 0)+1);
            fireworkEffects.setSmokeSize(1);
        } else {
            fireworkEffects.setSmokeSize(fileConfig.getInt(path+".FireworkEffects.SmokeSize"));
        }
        fireworkEffects.setPower(fileConfig.getInt(path + ".FireworkEffects.Power"));
        if(fileConfig.get(path + ".FireworkEffects.Type") == null) {
            if(tubeId == null) {
                RealFireworks.getInstance().getLogger().warning("You are missing the type with firework '" + id + "' so the type will be BALL");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            } else {
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
                RealFireworks.getInstance().getLogger().warning("You are missing the type with firework '" + id + "' and tubeID '" + tubeId + "' so the type will be BALL");
            }
            fireworkEffects.setType(FireworkEffect.Type.BALL);
        } else {
            fireworkEffects.setType(FireworkEffect.Type.valueOf(fileConfig.getString(path + ".FireworkEffects.Type")));
        }
        if (fileConfig.get(path + ".FireworkEffects.Red") == null || fileConfig.get(path + ".FireworkEffects.Green") == null || fileConfig.get(path + ".FireworkEffects.Blue") == null) {
            if(tubeId == null) {
                RealFireworks.getInstance().getLogger().warning("You are missing color(s) with firework '" + id + "' so the color will be black");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            } else {
                RealFireworks.getInstance().getLogger().warning("You are missing color(s) with firework '" + id + "' and tubeID '" + tubeId + "' so the color will be black");
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
        }
        fireworkEffects.setColor(
                fileConfig.getInt(path + ".FireworkEffects.Red"),
                fileConfig.getInt(path + ".FireworkEffects.Green"),
                fileConfig.getInt(path + ".FireworkEffects.Blue"));
        fireworkEffects.setFade(fileConfig.getBoolean(path + ".FireworkEffects.Fade"));
        if (fireworkEffects.getFade()) {
            if (fileConfig.get(path + ".FireworkEffects.FadeRed") == null || fileConfig.get(path + ".FireworkEffects.FadeGreen") == null || fileConfig.get(path + ".FireworkEffects.FadeBlue") == null) {
                if(tubeId == null) {
                    RealFireworks.getInstance().getLogger().warning("You are missing fade color(s) with firework '" + id + "' so the fade will be partly black");
                    warnings.put(file, warnings.getOrDefault(file, 0)+1);
                } else {
                    RealFireworks.getInstance().getLogger().warning("You are missing fade color(s) with firework '" + id + "' and tubeID '" + tubeId + "' so the fade will be partly black");
                    warnings.put(file, warnings.getOrDefault(file, 0)+1);
                }
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


    public static void reload() {
        loadFireworks();
    }

    public static Map<File, Map<String, FireworkInfo>> getFireworksList() {
        return fireworksList;
    }

    public static Map<File, Integer> getWarnings() {
        return warnings;
    }

    public static File getFolder() {
        return folder;
    }
}
