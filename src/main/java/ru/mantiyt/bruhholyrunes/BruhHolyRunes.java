package ru.mantiyt.bruhholyrunes;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.mantiyt.bruhholyrunes.commands.CommandExecutor;
import ru.mantiyt.bruhholyrunes.item.ItemRune;
import ru.mantiyt.bruhholyrunes.listener.AnvilListener;
import ru.mantiyt.bruhholyrunes.listener.TotemListener;
import ru.mantiyt.bruhholyrunes.manager.RuneManager;

@Getter
public final class BruhHolyRunes extends JavaPlugin
{
    @Getter
    private static BruhHolyRunes instance;
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
