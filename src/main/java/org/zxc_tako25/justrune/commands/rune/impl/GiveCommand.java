package org.zxc_tako25.justrune.commands.rune.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.zxc_tako25.justrune.JustRune;
import org.zxc_tako25.justrune.commands.SubCommands;
import org.zxc_tako25.justrune.item.AbstractItem;
import org.zxc_tako25.justrune.item.ItemRune;
import org.zxc_tako25.justrune.item.type.RuneType;
import org.zxc_tako25.justrune.utils.ColorUtils;

public class GiveCommand extends SubCommands
{
    @Override
    public String getName()
    {
        return "give";
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        JustRune plugin = JustRune.getInstance();
        ItemRune itemRune = plugin.getItemRune();
        if (args.length < 3)
        {
            sender.sendMessage(ColorUtils.color("&x&0&0&D&8&F&F▶ &fИспользование: &x&0&0&D&8&F&F/rune give [тип] [игрок] [кол-во]"));
            return;
        }

        RuneType runeType = getRuneName(args[1]);
        if (runeType == null)
        {
            sender.sendMessage(ColorUtils.color("&x&0&0&D&8&F&F▶ &fИспользование: &x&0&0&D&8&F&F/rune give [тип] [игрок] [кол-во]"));
            return;
        }

        AbstractItem rune = plugin.getRuneManager().getRune(runeType);
        if (rune == null)
        {
            sender.sendMessage(ColorUtils.color("&x&0&0&D&8&F&F▶ &fИспользование: &x&0&0&D&8&F&F/rune give [тип] [игрок] [кол-во]"));
            return;
        }

        Player target = Bukkit.getPlayer(args[2]);
        if (target == null)
        {
            sender.sendMessage(ColorUtils.color("&x&F&F&0&0&0&0▶ &fИгрок не найден"));
            return;
        }

        int amount = 1;
        if (args.length >= 4)
        {
            try
            {
                amount = Integer.parseInt(args[3]);
                if (amount <= 0)
                    amount = 1;
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(ColorUtils.color("&x&0&0&D&8&F&F▶ &fИспользование: &x&0&0&D&8&F&F/rune give [тип] [игрок] [кол-во]"));
                return;
            }
        }
        target.getInventory().addItem(itemRune.giveItem(rune, amount));
        sender.sendMessage(ColorUtils.color("&x&0&0&F&F&8&D▶ &fВы выдали &x&0&0&F&F&8&D" + amount + " &fрун типа &x&0&0&F&F&8&D" + runeType.name() + " &fигроку &x&0&0&F&F&8&D" + target.getName()));
    }

    private RuneType getRuneName(String name)
    {
        for (RuneType type : RuneType.values())
            if (type.name().equalsIgnoreCase(name))
                return type;
        return null;
    }
}
