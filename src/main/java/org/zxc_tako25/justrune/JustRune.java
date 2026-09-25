package org.zxc_tako25.justrune;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.zxc_tako25.justrune.commands.RuneCommand;
import org.zxc_tako25.justrune.rune.item.ItemRune;
import org.zxc_tako25.justrune.rune.listener.AnvilListener;
import org.zxc_tako25.justrune.rune.listener.TotemListener;
import org.zxc_tako25.justrune.rune.manager.RuneManager;

@Getter
public final class JustRune extends JavaPlugin
{
    @Getter
    private static JustRune instance;
    private ItemRune itemRune;
    private RuneManager runeManager;

    @Override
    public void onEnable()
    {
        instance = this;
        itemRune = new ItemRune();
        runeManager = new RuneManager(this);

        new RuneCommand().register(this);
        this.getServer().getPluginManager().registerEvents(new AnvilListener(this, runeManager), this);
        this.getServer().getPluginManager().registerEvents(new TotemListener(this, runeManager), this);
    }
}
