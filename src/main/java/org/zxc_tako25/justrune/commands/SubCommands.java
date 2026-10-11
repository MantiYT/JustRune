package org.zxc_tako25.justrune.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommands
{
    public abstract String getName();
    public abstract void execute(CommandSender sender, String[] args);
}
