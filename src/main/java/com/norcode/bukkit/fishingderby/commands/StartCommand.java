package com.norcode.bukkit.fishingderby.commands;

import com.norcode.bukkit.metalcore.MetalCorePlugin;
import com.norcode.bukkit.metalcore.command.BaseCommand;

/**
 * Created with IntelliJ IDEA.
 * User: ulchm
 * Date: 14/01/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class StartCommand extends BaseCommand {
    public StartCommand(MetalCorePlugin plugin) {
        super(plugin, "start", new String[] {}, "fishingderby.command.start", new String[] {});
    }
}
