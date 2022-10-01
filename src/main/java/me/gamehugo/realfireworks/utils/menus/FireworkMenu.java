package me.gamehugo.realfireworks.utils.menus;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.files.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FireworkMenu {

    public static void open(Player player, File file) {
        if(file == null) {
            final Inventory inventory = Bukkit.createInventory(null, size(RealFireworks.getFireworks().getFireworksList().size()), Messages.color("&6Firework Menu"));
            int count = 0;
            for (File files : RealFireworks.getFireworks().getFireworksList().keySet()) {
                if(files == null) continue;
                List<String> lore = new ArrayList<>();
                lore.add(Messages.color("&6Fireworks: &e"+RealFireworks.getFireworks().getFireworksList().get(files).size()));
                if(RealFireworks.getFireworks().getErrors().containsKey(files)) {
                    lore.add(Messages.color("&6Errors: &c"+RealFireworks.getFireworks().getErrors().get(files)));
                }
                if(RealFireworks.getFireworks().getWarnings().containsKey(files)) {
                    lore.add(Messages.color("&4Warnings: &c"+RealFireworks.getFireworks().getWarnings().get(files)));
                }
                inventory.setItem(count, createItem("&a"+files.getName(), lore));
                count++;
            }
            player.openInventory(inventory);
            return;
        }
        final Inventory inventory = Bukkit.createInventory(null, size(RealFireworks.getFireworks().getFireworksList().get(file).size()), Messages.color("&a"+file.getName()));
        if (!(RealFireworks.getFireworks().getFireworksList().get(file).size() > 54) && RealFireworks.getFireworks().getFireworksList().get(file).size() > 0) {
            int count = 0;
            for (FireworkInfo fireworkInfo : RealFireworks.getFireworks().getFireworksList().get(file).values()) {
                List<String> lore = new ArrayList<>();
                lore.add(Messages.color("&6Type: &e"+fireworkInfo.getFireworkType().name()));
                inventory.setItem(count, createItem(fireworkInfo.getName(), lore));
                count++;
            }
        } else {
            player.sendMessage(Messages.color("&cCannot open menu... If you are a server administrator check console"));
            RealFireworks.getInstance().getLogger().severe("Too many RealFireworks.getFireworks()... max is 54 and yours is " + RealFireworks.getFireworks().getFireworksList().size() + ".");
        }
        player.openInventory(inventory);
    }

    private static ItemStack createItem(String name, List<String> lore) {
        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta != null) {
            itemMeta.setDisplayName(Messages.color(name));
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
        }
        return item;
    }

    private static int size(int size) {
        if (size < 10) {
            return 9;
        } else if (size < 19) {
            return 18;
        } else if (size < 28) {
            return 27;
        } else {
            return 54;
        }
    }
}
