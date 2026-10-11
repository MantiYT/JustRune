package org.zxc_tako25.justrune;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.zxc_tako25.justrune.commands.CommandExecutor;
import org.zxc_tako25.justrune.item.ItemRune;
import org.zxc_tako25.justrune.listener.AnvilListener;
import org.zxc_tako25.justrune.listener.TotemListener;
import org.zxc_tako25.justrune.manager.RuneManager;

@Getter
public final class JustRune extends JavaPlugin
{
    @Getter
    private static JustRune instance;
    private ItemRune itemRune;
    private RuneManager runeManager;
    private CommandExecutor commandExecutor;

    @Override
    public void onEnable()
    {
        instance = this;
        itemRune = new ItemRune();
        runeManager = new RuneManager(this);
        Bukkit.getConsoleSender().sendMessage("§a" + getDescription().getName() + ": Успешный запуск! §bплагин создан для сборки §aHW-LITE от t.me/BruhStudio, §bАвтор данного плагина: t.me/faiacha_finx");

        commandExecutor = new CommandExecutor(this);
        commandExecutor.register();

        this.getServer().getPluginManager().registerEvents(new AnvilListener(this, runeManager), this);
        this.getServer().getPluginManager().registerEvents(new TotemListener(this, runeManager), this);
    }

    public void onDisable()
    {
        Bukkit.getConsoleSender().sendMessage("§c" + getDescription().getName() + " выключается...");
    }
}
