package com.norcode.bukkit.fishingderby.commands;

import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.metalcore.command.BaseCommand;
import com.norcode.bukkit.metalcore.command.CommandError;
import org.bukkit.command.CommandSender;

import java.util.LinkedList;

public class DerbyCommand extends BaseCommand {
    public DerbyCommand(MetalCorePlugin plugin) {
        super(plugin, "derby", new String[] {}, "fishingderby.command", new String[] {});
        registerSubcommand(new JoinCommand(plugin));
        registerSubcommand(new StartCommand(plugin));
        registerSubcommand(new StopCommand(plugin));
    }

    @Override
    protected void onExecute(CommandSender commandSender, String label, LinkedList<String> args) throws CommandError {
        commandSender.sendMessage("You typed /derby!");
    }


}
