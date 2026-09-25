package org.zxc_tako25.justrune.rune.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.zxc_tako25.justrune.rune.item.AbstractRune;
import org.zxc_tako25.justrune.rune.item.impl.Heal;
import org.zxc_tako25.justrune.rune.item.impl.Undying;
import org.zxc_tako25.justrune.rune.item.type.RuneType;

import java.util.HashMap;
import java.util.Map;

public class RuneManager
{
    private final Map<RuneType, AbstractRune> runesByType = new HashMap<>();
    private final Map<String, AbstractRune> runesById = new HashMap<>();

    public RuneManager(JavaPlugin plugin)
    {
        registerRune(new Undying(plugin));
        registerRune(new Heal(plugin));
    }

    private void registerRune(AbstractRune rune)
    {
        runesByType.put(rune.getType(), rune);
        runesById.put(rune.getRuneId(), rune);
    }

    public AbstractRune getRune(RuneType type)
    {
        return runesByType.get(type);
    }

    public AbstractRune getRuneById(String id)
    {
        return runesById.get(id);
    }

    public Map<RuneType, AbstractRune> getAllRunes()
    {
        return new HashMap<>(runesByType);
    }
}
