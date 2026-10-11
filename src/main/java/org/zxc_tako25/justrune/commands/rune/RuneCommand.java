package org.zxc_tako25.justrune.commands.rune;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.zxc_tako25.justrune.JustRune;
import org.zxc_tako25.justrune.commands.SubCommands;
import org.zxc_tako25.justrune.commands.rune.impl.GiveCommand;
import org.zxc_tako25.justrune.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class RuneCommand implements CommandExecutor, TabCompleter
{
    private final List<SubCommands> subCommands = new ArrayList<>();

    public RuneCommand()
    {
        subCommands.add(new GiveCommand());
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage(ColorUtils.color("&x&0&0&D&8&F&F▶ &fИспользование: &x&0&0&D&8&F&F/rune give [игрок] [тип]"));
            return true;
        }

        for (SubCommands subCommand : subCommands)
        {
            if (subCommand.getName().equalsIgnoreCase(args[0]))
            {
                subCommand.execute(sender, args);
                return true;
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args)
    {
        JustRune plugin = JustRune.getInstance();
        List<String> list = new ArrayList<>();

        if (args.length == 1)
        {
            list.add("give");
        }
        else if (args.length == 2)
        {
            if (args[0].equalsIgnoreCase("give"))
            {
                list.add("undying");
                list.add("heal");
            }
        }
        else if (args.length == 3)
        {
            if (args[0].equalsIgnoreCase("give"))
                plugin.getServer().getOnlinePlayers().forEach(player -> list.add(player.getName()));
        }
        return list;
    }
}
