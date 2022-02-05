package me.gamehugo.realfireworks.utils.menus;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.Chat;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.files.Fireworks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FireworkMenu {

    public static void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, size(), Chat.color("&6Firework Menu"));

        if (Fireworks.getFireworksList().size() > 0) {
            if (!(Fireworks.getFireworksList().size() > 54)) {
                int count = 0;
                for (FireworkInfo fireworkInfo : Fireworks.getFireworksList().values()) {
                    ItemStack item = new ItemStack(Material.FIREWORK_ROCKET);
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta != null) {
                        itemMeta.setDisplayName(Chat.color(fireworkInfo.getName()));
                        item.setItemMeta(itemMeta);
                    }
                    inventory.setItem(count, item);
                    count++;
                }
            } else {
                player.sendMessage(Chat.color("&cCannot open menu... If you are a server administrator check console"));
                RealFireworks.getInstance().getLogger().severe("Too many fireworks... max is 54 and yours is " + Fireworks.getFireworksList().size() + ".");
            }
        }
        player.openInventory(inventory);
    }

    private static int size() {
        if (Fireworks.getFireworksList().size() < 10) {
            return 9;
        } else if (Fireworks.getFireworksList().size() < 19) {
            return 18;
        } else if (Fireworks.getFireworksList().size() < 28) {
            return 27;
        } else {
            return 54;
        }
    }
}
