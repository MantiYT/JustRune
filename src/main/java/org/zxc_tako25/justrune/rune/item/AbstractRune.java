package org.zxc_tako25.justrune.rune.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.zxc_tako25.justrune.rune.item.type.RuneType;

import java.util.List;

@Getter
@RequiredArgsConstructor
public abstract class AbstractRune
{
    protected final JavaPlugin plugin;
    protected final RuneType type;
    protected final NamespacedKey runeKey = new NamespacedKey("justrune", "rune-id");

    public abstract String getRuneId();
    public abstract String getDisplayName();
    public abstract Material getMaterial();
    public abstract List<String> getLore();
    public abstract void applyEffect(Player player);
    public abstract String getEffectDisplay();
}
