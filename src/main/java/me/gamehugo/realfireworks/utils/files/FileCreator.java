package me.gamehugo.realfireworks.utils.files;

import me.gamehugo.realfireworks.RealFireworks;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@SuppressWarnings("unused")
public class FileCreator {

    public static File createFile(String filename) {
        File file = new File(RealFireworks.getInstance().getDataFolder(), filename);
        if(!file.exists()) {
            if(RealFireworks.getInstance().getResource(filename) != null) {
                RealFireworks.getInstance().saveResource(filename, false);
            } else {
                try {
                    if(file.createNewFile()) return file;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
    public static File createFile(String path, String filename) {
        File folder = new File(RealFireworks.getInstance().getDataFolder()+path);
        File file = new File(folder, filename);
        if(!folder.exists()) {
            if(!folder.mkdirs()) return null;
        }
        if(!file.exists()) {
            if(RealFireworks.getInstance().getResource(path+"/"+filename) != null) {
                RealFireworks.getInstance().saveResource(path+"/"+filename, false);
            } else {
                try {
                    if (file.createNewFile()) return file;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
    public static FileConfiguration createConfig(File file) {
        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return fileConfig;
    }

    public static YamlConfiguration getDefault(String filename) {
        return YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(RealFireworks.getInstance().getResource(filename)), StandardCharsets.UTF_8));
    }
}
