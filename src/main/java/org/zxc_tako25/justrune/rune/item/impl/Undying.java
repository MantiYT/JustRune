package org.zxc_tako25.justrune.rune.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.zxc_tako25.justrune.rune.item.AbstractRune;
import org.zxc_tako25.justrune.rune.item.type.RuneType;
import org.zxc_tako25.justrune.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class Undying extends AbstractRune
{
    private static final String RUNE_ID = "undying";

    public Undying(JavaPlugin plugin)
    {
        super(plugin, RuneType.UNDYING);
    }

    @Override
    public String getRuneId()
    {
        return RUNE_ID;
    }

    @Override
    public String getDisplayName()
    {
        return ColorUtils.color("&6«Бессмертие»");
    }

    @Override
    public Material getMaterial()
    {
        return Material.ORANGE_DYE;
    }

    @Override
    public List<String> getLore()
    {
        List<String> lore = new ArrayList<>();
        lore.add(ColorUtils.color("&8Эффект руны"));
        lore.add(ColorUtils.color(" "));
        lore.add(ColorUtils.color(" &7Особенности:"));
        lore.add(ColorUtils.color(" &6 - после активации тотема с этим эффектом,"));
        lore.add(ColorUtils.color(" &6   Вы получите неуязвимость к урону"));
        lore.add(ColorUtils.color(" &6   продолжительностью 3 секунды;"));
        lore.add(ColorUtils.color(" &6 - возможность наложить данный эффект"));
        lore.add(ColorUtils.color(" &6   на тотем через наковальню;"));
        lore.add(ColorUtils.color(" "));
        return lore;
    }

    @Override
    public void applyEffect(Player player)
    {
        player.setInvulnerable(true);

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                player.setInvulnerable(false);
            }
        }.runTaskLater(plugin, 60L);
    }

    public String getEffectDisplay()
    {
        return ColorUtils.color(" &6• Бессмертие");
    }
}
