package com.norcode.bukkit.fishingderby;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FishingListener implements Listener {
    private final FishingDerby plugin;

    public FishingListener(FishingDerby plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection cfg = plugin.getPlayerData(player);
        if (cfg.getBoolean("entered-derby")) {
            player.sendMessage("You are still in the ongoing fishing derby.  Catch more fish and return to the starting spot to claim your prizes.");
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCatchFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            Player player = event.getPlayer();
            Item item = (Item) event.getCaught();
            ItemStack fishStack = item.getItemStack();
            if (fishStack.getType() != Material.RAW_FISH) {
                return;
            }
            ItemMeta meta = fishStack.getItemMeta();
            List<String> lore = new ArrayList<String>();
            Double length = plugin.generateRandomLength();
            lore.add("Length: " + length + " " + plugin.getConfig().getString("length-units"));
            lore.add("Weight: " + plugin.generateRandomWeight(length) + " " + plugin.getConfig().getString("weight-units"));
            meta.setLore(lore);
            fishStack.setItemMeta(meta);
            player.sendMessage("You caught a fish!  You can submit this to the derby by equiping it and typing /derby submit");
        }
    }
}
