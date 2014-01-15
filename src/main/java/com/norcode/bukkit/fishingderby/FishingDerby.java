package com.norcode.bukkit.fishingderby;

import com.norcode.bukkit.fishingderby.commands.DerbyCommand;
import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.fishingderby.FishingListener;

import java.util.Random;

public class FishingDerby extends MetalCorePlugin {

    private Random random = new Random();

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
