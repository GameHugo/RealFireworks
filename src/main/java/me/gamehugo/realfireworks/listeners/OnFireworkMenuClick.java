package me.gamehugo.realfireworks.listeners;

import me.gamehugo.realfireworks.RealFireworks;
import me.gamehugo.realfireworks.utils.files.Messages;
import me.gamehugo.realfireworks.utils.firework.Fireworks;
import me.gamehugo.realfireworks.utils.FireworkInfo;
import me.gamehugo.realfireworks.utils.menus.FireworkMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Objects;

public class OnFireworkMenuClick implements Listener {

    @EventHandler
    public void OnInvClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equals(Messages.color("&6Firework Menu")) || isTitle(e.getView().getTitle())) {
            e.setCancelled(true);
            if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getItemMeta() == null) return;
            for(File files : RealFireworks.getFireworks().getFireworksList().keySet()) {
                if(files == null) continue;
                if(e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§a", "").equalsIgnoreCase(files.getName())) {
                    FireworkMenu.open((Player) e.getWhoClicked(), files);
                }
                for (FireworkInfo fireworkInfo : RealFireworks.getFireworks().getFireworksList().get(files).values()) {
                    if (Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName().equals(Messages.color(fireworkInfo.getName()))) {
                        ItemStack item = new ItemStack(Material.FIREWORK_ROCKET, 1);
                        ItemMeta itemMeta = item.getItemMeta();
                        if (itemMeta != null) {
                            itemMeta.setDisplayName(Messages.color(fireworkInfo.getName()));
                            itemMeta.setLocalizedName(fireworkInfo.getId());
                            if (fireworkInfo.getLore() != null) {
                                itemMeta.setLore(fireworkInfo.getLore());
                            }
                            item.setItemMeta(itemMeta);
                        }
                        e.getWhoClicked().getInventory().addItem(item);
                        return;
                    }
                }
            }
        }
    }

    public boolean isTitle(String s) {
        for(File files : RealFireworks.getFireworks().getFireworksList().keySet()) {
            if(files == null) continue;
            if(s.replaceAll("§a", "").equalsIgnoreCase(files.getName())) return true;
        }
        return false;
    }
}
