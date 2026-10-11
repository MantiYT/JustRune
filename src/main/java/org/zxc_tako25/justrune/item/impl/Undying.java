package org.zxc_tako25.justrune.item.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.zxc_tako25.justrune.item.AbstractItem;
import org.zxc_tako25.justrune.item.type.RuneType;
import org.zxc_tako25.justrune.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class Undying extends AbstractItem
{
    private static final String RUNE_ID = "immortality";

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
        return ColorUtils.color("&x&c&c&7&7&0&0Р&x&d&6&7&d&0&0у&x&e&0&8&3&0&0н&x&e&a&8&9&0&0а&x&f&4&8&f&0&0 &x&f&f&9&5&0&0«&x&f&f&9&5&0&0Б&x&f&f&9&5&0&0е&x&f&f&9&5&0&0с&x&f&f&9&5&0&0с&x&f&f&9&5&0&0м&x&f&6&9&0&0&0е&x&e&e&8&b&0&0р&x&e&5&8&6&0&0т&x&d&d&8&1&0&0и&x&d&4&7&c&0&0е»");
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
        lore.add(ColorUtils.color(""));
        lore.add(ColorUtils.color(" &7Особенности:"));
        lore.add(ColorUtils.color(" &6 - после активации тотема с этим эффектом,"));
        lore.add(ColorUtils.color(" &0.&6   Вы получите неуязвимость к урону"));
        lore.add(ColorUtils.color(" &0.&6   продолжительностью 3 секунды;"));
        lore.add(ColorUtils.color(" &6 - возможность наложить данный эффект"));
        lore.add(ColorUtils.color(" &0.&6   на тотем через наковальню;"));
        lore.add(ColorUtils.color(""));

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