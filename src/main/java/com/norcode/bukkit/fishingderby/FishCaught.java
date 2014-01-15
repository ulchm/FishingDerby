package com.norcode.bukkit.fishingderby;

import org.bukkit.entity.Player;

public class FishCaught{
    private Player player;
    private Double weight;
    private Double height;

    public FishCaught(Player player, Double weight, Double height) {
        this.player = player;
        this.weight = weight;
        this.height = height;
    }

    public Player getPlayer() {
        return player;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getHeight() {
        return height;
    }
}
