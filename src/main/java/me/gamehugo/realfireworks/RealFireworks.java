package me.gamehugo.realfireworks;

import me.gamehugo.realfireworks.commands.RealFireworksCMD;
import me.gamehugo.realfireworks.listeners.OnFireworkIgnite;
import me.gamehugo.realfireworks.listeners.OnFireworkMenuClick;
import me.gamehugo.realfireworks.utils.Chat;
import me.gamehugo.realfireworks.utils.files.Config;
import me.gamehugo.realfireworks.utils.files.Messages;
import me.gamehugo.realfireworks.utils.fireworktypes.Firework;
import me.gamehugo.realfireworks.utils.files.Fireworks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RealFireworks extends JavaPlugin {

    private static RealFireworks instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(Chat.color("&6Loading RealFireworks!"));
        if(!loadInstances())return;

        Config.setup();
        Messages.setup();
        Fireworks.setup();

        Fireworks.loadFireworks();

        Objects.requireNonNull(getCommand("realfireworks")).setExecutor(new RealFireworksCMD());

        getServer().getPluginManager().registerEvents(new OnFireworkMenuClick(), this);
        getServer().getPluginManager().registerEvents(new OnFireworkIgnite(), this);
        getLogger().info(Chat.color("&eVersion: "+getDescription().getVersion()));
        getLogger().info(Chat.color("&eMade with &c❤  &eby "+getDescription().getAuthors().toString().substring(1, getDescription().getAuthors().toString().length()-1)));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean loadInstances() {
        if(getDescription().getName().equals("RealFireworks")) {
            instance = this;
            new Firework().setup();
            return true;
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }
    }

    public static RealFireworks getInstance() {
        return instance;
    }
}
