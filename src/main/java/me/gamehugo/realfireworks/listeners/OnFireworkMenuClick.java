package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.Chat;
import me.gamehugo.realfireworks.utils.files.Config;
import me.gamehugo.realfireworks.utils.files.Fireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class OnFireworkMenuClick implements Listener {

    @EventHandler
    public void OnInvClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals(Chat.color("&6Firework Menu"))) {
            e.setCancelled(true);
            for(FireworkInfo fireworkInfo : Fireworks.getFireworksList().values()) {
                if(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName().equals(Chat.color(fireworkInfo.getName()))) {
                    ItemStack item = new ItemStack(Material.FIREWORK_ROCKET, 1);
                    ItemMeta itemMeta = item.getItemMeta();
                    if(itemMeta != null) {
                        itemMeta.setDisplayName(Chat.color(fireworkInfo.getName()));
                        if(fireworkInfo.getLore() != null) {
                            itemMeta.setLore(fireworkInfo.getLore());
                        }
                        item.setItemMeta(itemMeta);
                    } else {
                        if(Config.getConfig().getBoolean("Debug")) RealFireworks.getInstance().getLogger().severe("Item meta null");
                    }
                    e.getWhoClicked().getInventory().addItem(item);
                }
            }
        }
    }
}
