package me.gamehugo.realfireworks.commands;

import me.gamehugo.realfireworks.utils.Chat;
import me.gamehugo.realfireworks.utils.files.Fireworks;
import me.gamehugo.realfireworks.utils.files.Messages;
import me.gamehugo.realfireworks.utils.menus.FireworkMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RealFireworksCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {sender.sendMessage(Chat.color("&cYou can only do this as player.")); return false;}
        if(!sender.hasPermission("realfireworks.use")) {sender.sendMessage(Messages.get("noPerms")); return false;}
        Player p = (Player) sender;
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("reload")) {
                Fireworks.reload();
                p.sendMessage(Chat.color("&aReloaded fireworks"));
            }
            return true;
        }
        FireworkMenu.open(p);
        return true;
    }
}
