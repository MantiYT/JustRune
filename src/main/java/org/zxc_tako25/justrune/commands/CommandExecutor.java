package org.zxc_tako25.justrune.commands;

import org.bukkit.command.PluginCommand;
import org.zxc_tako25.justrune.JustRune;
import org.zxc_tako25.justrune.commands.rune.RuneCommand;

public class CommandExecutor {

    private final JustRune plugin;

    public CommandExecutor(JustRune plugin) {
        this.plugin = plugin;
    }

    public void register() {
        PluginCommand command = this.plugin.getCommand("rune");
        if (command == null) {
            return;
        }
        RuneCommand executor = new RuneCommand();
        command.setExecutor(executor);
        command.setTabCompleter(executor);
    }
}