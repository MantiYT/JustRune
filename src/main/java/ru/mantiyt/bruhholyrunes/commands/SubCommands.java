package ru.mantiyt.bruhholyrunes.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommands
{
    public abstract String getName();
    public abstract void execute(CommandSender sender, String[] args);
}
