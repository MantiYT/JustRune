package ru.mantiyt.bruhholyrunes.commands;

import org.bukkit.command.PluginCommand;
import ru.mantiyt.bruhholyrunes.BruhHolyRunes;
import ru.mantiyt.bruhholyrunes.commands.rune.RuneCommand;

public class CommandExecutor {

    private final BruhHolyRunes plugin;

    public CommandExecutor(BruhHolyRunes plugin) {
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