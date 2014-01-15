package com.norcode.bukkit.fishingderby.commands;

import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.metalcore.command.BaseCommand;
import com.norcode.bukkit.metalcore.command.CommandError;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;

public class SubmitCommand extends BaseCommand {
    public SubmitCommand(MetalCorePlugin plugin) {
        super(plugin, "submit", new String[] {}, "fishingderby.command.submit", new String[] {});
    }

    @Override
    protected void onExecute(CommandSender commandSender, String label, LinkedList<String> args) throws CommandError {
        commandSender.sendMessage("You typed /derby submit!");
        if (!(commandSender instanceof Player)) {
            return;
        }
        Player player = (Player) commandSender;
        ItemStack stack = player.getItemInHand();
        ItemMeta meta = stack.getItemMeta();
        ConfigurationSection cfg = plugin.getPlayerData(player);

        String strLength = meta.getLore().get(0).split(":")[1].trim();
        strLength = strLength.split(plugin.getConfig().getString("length-units"))[0];
        String strWeight = meta.getLore().get(1).split(":")[1].trim();
        strWeight = strWeight.split(plugin.getConfig().getString("weight-units"))[0];

        /* TO-DO: Decide how I want to store this */
        player.sendMessage("The fish has been added to your derby total.");
    }
}
