package com.norcode.bukkit.fishingderby.commands;

import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.metalcore.command.BaseCommand;
import com.norcode.bukkit.metalcore.command.CommandError;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class JoinCommand extends BaseCommand {
    public JoinCommand(MetalCorePlugin plugin) {
        super(plugin, "join", new String[] {}, "fishingderby.command.join", new String[] {});
    }

    @Override
    protected void onExecute(CommandSender commandSender, String label, LinkedList<String> args) throws CommandError {
        commandSender.sendMessage("You typed /derby join!");
    }
}
