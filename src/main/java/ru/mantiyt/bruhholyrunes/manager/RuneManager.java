package ru.mantiyt.bruhholyrunes.manager;

import org.bukkit.plugin.java.JavaPlugin;
import ru.mantiyt.bruhholyrunes.item.AbstractItem;
import ru.mantiyt.bruhholyrunes.item.impl.Heal;
import ru.mantiyt.bruhholyrunes.item.impl.Undying;
import ru.mantiyt.bruhholyrunes.item.type.RuneType;

import java.util.HashMap;
import java.util.Map;

public class RuneManager
{
    private final Map<RuneType, AbstractItem> runesByType = new HashMap<>();
    private final Map<String, AbstractItem> runesById = new HashMap<>();

    public RuneManager(JavaPlugin plugin)
    {
        registerRune(new Undying(plugin));
        registerRune(new Heal(plugin));
    }

    private void registerRune(AbstractItem rune)
    {
        runesByType.put(rune.getType(), rune);
        runesById.put(rune.getRuneId(), rune);
    }

    public AbstractItem getRune(RuneType type)
    {
        return runesByType.get(type);
    }

    public AbstractItem getRuneById(String id)
    {
        return runesById.get(id);
    }

    public Map<RuneType, AbstractItem> getAllRunes()
    {
        return new HashMap<>(runesByType);
    }
}
