package me.gamehugo.realfireworks;

import me.gamehugo.realfireworks.commands.RealFireworksCMD;
import me.gamehugo.realfireworks.listeners.OnFireworkDamage;
import me.gamehugo.realfireworks.listeners.OnFireworkExplode;
import me.gamehugo.realfireworks.listeners.OnFireworkIgnite;
import me.gamehugo.realfireworks.listeners.OnFireworkMenuClick;
import me.gamehugo.realfireworks.utils.files.Config;
import me.gamehugo.realfireworks.utils.files.Messages;
import me.gamehugo.realfireworks.utils.firework.Fireworks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RealFireworks extends JavaPlugin {
    private static RealFireworks instance;

    @Override
    public void onLoad() {
        loadInstances();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        long timeAtStart = System.currentTimeMillis();
        if(Config.getConfig().getBoolean("Debug")) getLogger().info(" ");

        Messages.setup();
        Fireworks.setup();

        Fireworks.loadFireworks();

        Objects.requireNonNull(getCommand("realfireworks")).setExecutor(new RealFireworksCMD());

        getServer().getPluginManager().registerEvents(new OnFireworkMenuClick(), this);
        getServer().getPluginManager().registerEvents(new OnFireworkIgnite(), this);
        getServer().getPluginManager().registerEvents(new OnFireworkDamage(), this);
        getServer().getPluginManager().registerEvents(new OnFireworkExplode(), this);
        long timeTakenInMS = System.currentTimeMillis()-timeAtStart;
        Bukkit.getConsoleSender().sendMessage("["+getDescription().getName()+"] "+Messages.color("&6Done! Loaded in "+timeTakenInMS+"ms"));
        Bukkit.getConsoleSender().sendMessage("["+getDescription().getName()+"] "+Messages.color("&eVersion: "+getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage("["+getDescription().getName()+"] "+Messages.color("&eMade with &c❤  &eby "+getDescription().getAuthors().toString().substring(1, getDescription().getAuthors().toString().length()-1)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadInstances() {
        if(getDescription().getName().equals("RealFireworks") && getDescription().getAuthors().contains("GameHugo_")) {
            instance = this;
            Config.setup();
        } else {
            getLogger().severe("You can't change the name of the plugin or the author!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public static RealFireworks getInstance() {
        return instance;
    }
}
