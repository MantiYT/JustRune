package ru.mantiyt.bruhholyrunes.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mantiyt.bruhholyrunes.item.AbstractItem;
import ru.mantiyt.bruhholyrunes.item.type.RuneType;
import ru.mantiyt.bruhholyrunes.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class Heal extends AbstractItem
{
    private static final String RUNE_ID = "vosstanovlenie";

    public Heal(JavaPlugin plugin)
    {
        super(plugin, RuneType.HEAL);
    }

    @Override
    public String getRuneId()
    {
        return RUNE_ID;
    }

    @Override
    public String getDisplayName()
    {
        return ColorUtils.color("&c«Восстановление»");
    }

    @Override
    public Material getMaterial()
    {
        return Material.RED_DYE;
    }

    @Override
    public List<String> getLore()
    {
        List<String> lore = new ArrayList<>();
        lore.add(ColorUtils.color("&8Эффект руны"));
        lore.add(ColorUtils.color(" "));
        lore.add(ColorUtils.color(" &7Особенности:"));
        lore.add(ColorUtils.color(" &c - после активации тотема с этим эффектом,"));
        lore.add(ColorUtils.color(" &c   у вас полностью восстановиться здоровье;"));
        lore.add(ColorUtils.color(" &c - возможность наложить данный эффект"));
        lore.add(ColorUtils.color(" &c   на тотем через наковальню;"));
        lore.add(ColorUtils.color(" "));
        return lore;
    }

    @Override
    public void applyEffect(Player player)
    {
        player.setHealth(player.getMaxHealth());
    }

    public String getEffectDisplay()
    {
        return ColorUtils.color(" &c• Восстановление");
    }
}
