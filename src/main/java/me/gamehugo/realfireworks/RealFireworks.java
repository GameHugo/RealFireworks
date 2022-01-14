package me.gamehugo.realfireworks;

import me.gamehugo.realfireworks.Commands.RealFireworksCMD;
import me.gamehugo.realfireworks.Listeners.OnFireworkIgnite;
import me.gamehugo.realfireworks.Listeners.OnFireworkMenuClick;
import me.gamehugo.realfireworks.Utils.Files.Config;
import me.gamehugo.realfireworks.Utils.Files.Messages;
import me.gamehugo.realfireworks.Utils.FireworkTypes.Firework;
import me.gamehugo.realfireworks.Utils.Menus.FireworkMenu;
import me.gamehugo.realfireworks.Utils.Files.Fireworks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RealFireworks extends JavaPlugin {

    private static RealFireworks instance;

    public static FireworkMenu fireworkMenu;

    @Override
    public void onEnable() {
        // Plugin startup logic
        loadInstances();
        getLogger().info("\n"+
                "&6Loading RealFireworks!\n"+
                "&eVersion: "+getDescription().getVersion()+"\n"+
                "&eMade with &c❤  &eby "+getDescription().getAuthors().toString().substring(1, getDescription().getAuthors().toString().length()-1)+"\n"
        );
        Config.setup();
        Messages.setup();
        Fireworks.setup();

        Fireworks.loadFireworks();

        Objects.requireNonNull(getCommand("realfireworks")).setExecutor(new RealFireworksCMD());

        getServer().getPluginManager().registerEvents(new OnFireworkMenuClick(), this);
        getServer().getPluginManager().registerEvents(new OnFireworkIgnite(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadInstances() {
        if(getDescription().getName().equals("RealFireworks")) {
            instance = this;
            fireworkMenu = new FireworkMenu();
            new Firework().setup();
        } else {
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public static RealFireworks getInstance() {
        return instance;
    }
}
