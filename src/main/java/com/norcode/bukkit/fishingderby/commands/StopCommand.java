package com.norcode.bukkit.fishingderby.commands;

import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.metalcore.command.BaseCommand;
import com.norcode.bukkit.metalcore.command.CommandError;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class StopCommand extends BaseCommand {
    public StopCommand(MetalCorePlugin plugin) {
        super(plugin, "stop", new String[] {}, "fishingderby.command.stop", new String[] {});
    }

    @Override
    protected void onExecute(CommandSender commandSender, String label, LinkedList<String> args) throws CommandError {
        commandSender.sendMessage("You typed /derby stop");
    }
}
