package me.gamehugo.realfireworks.utils.firework;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.CakeEffect;
import me.gamehugo.realfireworks.utils.FireworkEffects;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.files.Config;
import me.gamehugo.realfireworks.utils.files.FileCreator;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
public class Fireworks {

    private final File folder = new File(RealFireworks.getInstance().getDataFolder()+"/Fireworks");;
    private final Map<File, Map<String, FireworkInfo>> fireworksList = new HashMap<>();
    private final Map<File, Integer> errors = new HashMap<>();
    private final Map<File, Integer> warnings = new HashMap<>();
    private boolean fixWarnings;

    public void loadFireworks() {
        fireworksList.clear();
        errors.clear();
        warnings.clear();
        fixWarnings = Config.getConfig().getBoolean("FixWarnings");
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

    public FireworkInfo getFireworkInfo(File file, FileConfiguration fileConfig, String path) {
        FireworkInfo fireworkInfo = new FireworkInfo();
        fireworkInfo.setId(path);
        if(fileConfig.getString(path+".Name") == null) {
            RealFireworks.getInstance().getLogger().severe("Failed to load a firework...\nPATH: "+path);
            errors.put(file, errors.getOrDefault(file, 0)+1);
            return null;
        }
        fireworkInfo.setName(fileConfig.getString(path+".Name"));
        fireworkInfo.setLore(fileConfig.getStringList(path+".Lore"));
        if(FireworkType.getType(Objects.requireNonNull(fileConfig.getString(path+".FireworkType"))) == null) {
            RealFireworks.getInstance().getLogger().severe("Failed to load firework "+fileConfig.getString(path+".Name")+" invalid FireworkType\nPATH: "+path);
            errors.put(file, errors.getOrDefault(file, 0)+1);
            return null;
        }
        fireworkInfo.setFireworkType(FireworkType.getType(Objects.requireNonNull(fileConfig.getString(path+".FireworkType"))));
        if(fireworkInfo.getFireworkType().equals(FireworkType.CAKE) && fileConfig.get(path+".CakeEffects") == null) {
            RealFireworks.getInstance().getLogger().severe("Failed to load firework "+fileConfig.getString(path+".Name")+" no CakeEffects\nPATH: "+path);
            errors.put(file, errors.getOrDefault(file, 0)+1);
            return null;
        }
        if(fireworkInfo.getFireworkType().equals(FireworkType.CAKE)) {
            for(String id : Objects.requireNonNull(fileConfig.getConfigurationSection(path+".CakeEffects")).getKeys(false)) {
                CakeEffect cakeEffect = new CakeEffect();
                cakeEffect.setDelay(fileConfig.getInt(path+".CakeEffects."+id+".Delay"));
                cakeEffect.setFireworkEffects(convertFireworkEffects(file, fileConfig, path+".CakeEffects."+id));
                fireworkInfo.addTube(cakeEffect);
            }
        } else {
            if(fileConfig.get(path+".CakeEffects") != null) {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework "+fileConfig.getString(path+".Name")+" there is a CakeEffect and isn't a cake\nPATH: "+path);
                errors.put(file, errors.getOrDefault(file, 0)+1);
                return null;
            }
            fireworkInfo.setFireworkEffects(convertFireworkEffects(file, fileConfig, path));
        }
        return fireworkInfo;
    }

    public FireworkEffects convertFireworkEffects(File file, FileConfiguration fileConfig, String path) {
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
                errors.put(file, errors.getOrDefault(file, 0)+1);
            } else {
                RealFireworks.getInstance().getLogger().severe("Failed to load firework '" + id + "' and tubeID '" + tubeId + "' no FireworkEffects");
                errors.put(file, errors.getOrDefault(file, 0)+1);
            }
            return null;
        }
        if (fileConfig.get(path + ".FireworkEffects.Power") == null) {
            if(fixWarnings) {
                fileConfig.set(path + ".FireworkEffects.Power", 1);
                fireworkEffects.setPower(1);
                if(tubeId == null) {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no Power");
                } else {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no Power");
                }
                try {
                    fileConfig.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RealFireworks.getInstance().getLogger().warning("You are missing the Power in the FireworkEffects of " + id);
                fireworkEffects.setPower(1);
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
        } else {
            fireworkEffects.setPower(fileConfig.getInt(path + ".FireworkEffects.Power"));
        }
        if(fileConfig.get(path+".FireworkEffects.Smoke") == null) {
            if(fixWarnings) {
                fileConfig.set(path + ".FireworkEffects.Smoke", false);
                fireworkEffects.setSmoke(false);
                if(tubeId == null) {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no Smoke");
                } else {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no Smoke");
                }
                try {
                    fileConfig.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RealFireworks.getInstance().getLogger().warning("You are missing the Smoke in the FireworkEffects of " + id);
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
        } else {
            fireworkEffects.setSmoke(fileConfig.getBoolean(path+".FireworkEffects.Smoke"));
        }
        if(fireworkEffects.hasSmoke()) {
            if (fileConfig.get(path + ".FireworkEffects.SmokeIntensity") == null) {
                if (fixWarnings) {
                    fileConfig.set(path + ".FireworkEffects.SmokeIntensity", 1);
                    fireworkEffects.setSmokeIntensity(1);
                    if (tubeId == null) {
                        RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no SmokeIntensity");
                    } else {
                        RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no SmokeIntensity");
                    }
                    try {
                        fileConfig.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    RealFireworks.getInstance().getLogger().warning("You are missing the SmokeIntensity in the FireworkEffects of " + id);
                    warnings.put(file, warnings.getOrDefault(file, 0) + 1);
                }
            } else {
                fireworkEffects.setSmokeIntensity(fileConfig.getInt(path + ".FireworkEffects.SmokeIntensity"));
            }
            if (fileConfig.get(path + ".FireworkEffects.SmokeSize") == null) {
                if (fixWarnings) {
                    fileConfig.set(path + ".FireworkEffects.SmokeSize", 1);
                    fireworkEffects.setSmokeSize(1);
                    if (tubeId == null) {
                        RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no SmokeSize");
                    } else {
                        RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no SmokeSize");
                    }
                    try {
                        fileConfig.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    RealFireworks.getInstance().getLogger().warning("You are missing the SmokeSize in the FireworkEffects of " + id);
                    warnings.put(file, warnings.getOrDefault(file, 0) + 1);
                }
            } else {
                fireworkEffects.setSmokeSize(fileConfig.getInt(path + ".FireworkEffects.SmokeSize"));
            }
        }
        if(fileConfig.get(path + ".FireworkEffects.Type") == null) {
            if(fixWarnings) {
                fileConfig.set(path + ".FireworkEffects.Type", "BALL");
                fireworkEffects.setType(FireworkEffect.Type.BALL);
                if(tubeId == null) {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no Type");
                } else {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no Type");
                }
                try {
                    fileConfig.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RealFireworks.getInstance().getLogger().warning("You are missing the Type in the FireworkEffects of " + id);
                fireworkEffects.setType(FireworkEffect.Type.BALL);
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
        } else {
            fireworkEffects.setType(FireworkEffect.Type.valueOf(fileConfig.getString(path + ".FireworkEffects.Type")));
        }
        if (fileConfig.get(path + ".FireworkEffects.Red") == null || fileConfig.get(path + ".FireworkEffects.Green") == null || fileConfig.get(path + ".FireworkEffects.Blue") == null) {
            if(fixWarnings) {
                fileConfig.set(path + ".FireworkEffects.Red", 255);
                fileConfig.set(path + ".FireworkEffects.Green", 255);
                fileConfig.set(path + ".FireworkEffects.Blue", 255);
                fireworkEffects.setColor(255, 255, 255);
                if(tubeId == null) {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no Color");
                } else {
                    RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no Color");
                }
                try {
                    fileConfig.save(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                RealFireworks.getInstance().getLogger().warning("You are missing the Color in the FireworkEffects of " + id);
                fireworkEffects.setColor(255, 255, 255);
                warnings.put(file, warnings.getOrDefault(file, 0)+1);
            }
        } else {
            fireworkEffects.setColor(
                    fileConfig.getInt(path + ".FireworkEffects.Red"),
                    fileConfig.getInt(path + ".FireworkEffects.Green"),
                    fileConfig.getInt(path + ".FireworkEffects.Blue"));
        }
        fireworkEffects.setFade(fileConfig.getBoolean(path + ".FireworkEffects.Fade"));
        if (fireworkEffects.getFade()) {
            if (fileConfig.get(path + ".FireworkEffects.FadeRed") == null || fileConfig.get(path + ".FireworkEffects.FadeGreen") == null || fileConfig.get(path + ".FireworkEffects.FadeBlue") == null) {
                if(fixWarnings) {
                    fileConfig.set(path + ".FireworkEffects.FadeRed", 255);
                    fileConfig.set(path + ".FireworkEffects.FadeGreen", 255);
                    fileConfig.set(path + ".FireworkEffects.FadeBlue", 255);
                    fireworkEffects.setFadeColor(255, 255, 255);
                    if(tubeId == null) {
                        RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' no FadeColor");
                    } else {
                        RealFireworks.getInstance().getLogger().info("Fixed firework '" + id + "' and tubeID '" + tubeId + "' no FadeColor");
                    }
                    try {
                        fileConfig.save(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    RealFireworks.getInstance().getLogger().warning("You are missing the FadeColor in the FireworkEffects of " + id);
                    fireworkEffects.setFadeColor(255, 255, 255);
                    warnings.put(file, warnings.getOrDefault(file, 0)+1);
                }
            } else {
                fireworkEffects.setFadeColor(
                        fileConfig.getInt(path + ".FireworkEffects.FadeRed"),
                        fileConfig.getInt(path + ".FireworkEffects.FadeGreen"),
                        fileConfig.getInt(path + ".FireworkEffects.FadeBlue"));
            }
        }
        fireworkEffects.setFlicker(fileConfig.getBoolean(path + ".FireworkEffects.Flicker"));
        fireworkEffects.setTrail(fileConfig.getBoolean(path + ".FireworkEffects.Trail"));
        return fireworkEffects;
    }


    public void reload() {
        loadFireworks();
    }

    public Map<File, Map<String, FireworkInfo>> getFireworksList() {
        return fireworksList;
    }

    public Map<File, Integer> getErrors() {
        return errors;
    }
    public Map<File, Integer> getWarnings() {
        return warnings;
    }

    public File getFolder() {
        return folder;
    }
}
