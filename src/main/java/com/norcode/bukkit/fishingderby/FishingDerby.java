package com.norcode.bukkit.fishingderby;

import com.norcode.bukkit.fishingderby.commands.DerbyCommand;
import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.fishingderby.FishingListener;
import com.norcode.bukkit.metalcore.util.ConfigAccessor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.Random;

public class FishingDerby extends MetalCorePlugin {

    private Random random = new Random();
    public ConfigAccessor derbyCfg = new ConfigAccessor(this, "derby.yml");

    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new FishingListener(this), this);
        new DerbyCommand(this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    /***
     * Add Fish To Derby
     * @param player - player that caught the fish
     * @param fishStack - fish stack containing necessary lore
     * @return boolean, true if successful, false if not.
     */
    public boolean addFishToDerby(Player player, ItemStack fishStack) {
        ItemMeta meta = fishStack.getItemMeta();
        ConfigurationSection cfg = getPlayerData(player);

        String strLength = meta.getLore().get(0).split(":")[1].trim();
        strLength = strLength.split(getConfig().getString("length-units"))[0];
        String strWeight = meta.getLore().get(1).split(":")[1].trim();
        strWeight = strWeight.split(getConfig().getString("weight-units"))[0];

        List<FishCaught> fishCaughtList = (List) derbyCfg.getConfig().getList(player.getDisplayName() + "-fish-caught");
        if (fishCaughtList.size() < 5) {
            fishCaughtList.add(new FishCaught(player, Double.parseDouble(strLength), Double.parseDouble(strWeight)));
            derbyCfg.getConfig().set(player.getDisplayName() + "-fish-caught", fishCaughtList);
            derbyCfg.saveConfig();
            return true;
        } else {
            return false;
        }
    }

    public void clearDerbyResults() {

    }

    /***
     * Renerates a random length for a fish based on users configuration settings.
     * @return a random double that is fish length.
     */
    public double generateRandomLength() {
        Double minLength = this.getConfig().getDouble("min-fish-length");
        Double maxLength = this.getConfig().getDouble("max-fish-length");
        if (random.nextDouble() < 0.8) {
            //most commonly, reduce the max size available by half
            maxLength = maxLength * 0.5;
            if (random.nextDouble() < 0.75) {
                //take 25% of these remaining results and make sure they are big small fish.
                minLength = maxLength * 0.8;
            }
        } else {
            minLength = maxLength * 0.5;
        }

        maxLength = maxLength * 1000;
        minLength = minLength * 1000;
        Integer bigInt = random.nextInt(maxLength.intValue()-minLength.intValue()) + minLength.intValue();
        return bigInt / 1000;
    }


    /***
     * Generates a random weight for a fish based on a users configuration settings.
     * @return a random double that is fish weight.
     */
    public double generateRandomWeight(Double length) {
        Double weight = length * 0.5;
        if (random.nextDouble() > 0.8) {
           if (random.nextDouble() > 0.8) {
                /* really fat */
               weight = weight * length;
            } else {
               /* pretty fat */
               weight = weight * (0.8 * length);
           }
        }
        return weight.intValue() / 4;
    }
}
