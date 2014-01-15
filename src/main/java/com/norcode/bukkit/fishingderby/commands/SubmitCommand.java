package com.norcode.bukkit.fishingderby.commands;

import com.norcode.bukkit.fishingderby.FishingDerby;
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

    private FishingDerby fdPlugin = (FishingDerby) plugin;

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

        Boolean added = fdPlugin.addFishToDerby(player, stack);
        if (added) {
            player.sendMessage("The fish has been added to your derby total.");
        } else {
            player.sendMessage("This fish could not be added to your total.  Already caught your limit?");
        }
    }
}
