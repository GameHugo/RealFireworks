package me.gamehugo.realfireworks.commands;

import me.gamehugo.realfireworks.utils.files.Config;
import me.gamehugo.realfireworks.utils.firework.Fireworks;
import me.gamehugo.realfireworks.utils.files.Messages;
import me.gamehugo.realfireworks.utils.menus.FireworkMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RealFireworksCMD implements CommandExecutor, TabExecutor {
    private final List<String> strings = List.of("reload");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("realfireworks.use")) {sender.sendMessage(Messages.get("noPerms")); return false;}
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("reload")) {
                sender.sendMessage(Messages.color("&aReloading plugin..."));
                long timeAtStart = System.currentTimeMillis();
                Config.reload();
                Messages.reload();
                Fireworks.reload();
                long timeTakenInMS = System.currentTimeMillis()-timeAtStart;
                sender.sendMessage(Messages.color("&aReloaded plugin in "+timeTakenInMS+"ms"));
            }
            return true;
        }
        if(!(sender instanceof Player)) {sender.sendMessage(Messages.color("&cYou can only do this as player.")); return false;}
        Player p = (Player) sender;
        FireworkMenu.open(p, null);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(!sender.hasPermission("realfireworks.use")) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (String string : strings) {
            if(string.toLowerCase().startsWith(args[0].toLowerCase())) result.add(string);
        }
        return result;
    }
}
