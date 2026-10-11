package org.zxc_tako25.justrune.manager;

import org.bukkit.plugin.java.JavaPlugin;
import org.zxc_tako25.justrune.item.AbstractItem;
import org.zxc_tako25.justrune.item.impl.Heal;
import org.zxc_tako25.justrune.item.impl.Undying;
import org.zxc_tako25.justrune.item.type.RuneType;

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
