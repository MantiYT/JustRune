package org.zxc_tako25.justrune.rune.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.zxc_tako25.justrune.JustRune;
import org.zxc_tako25.justrune.rune.item.AbstractRune;
import org.zxc_tako25.justrune.rune.manager.RuneManager;
import org.zxc_tako25.justrune.utils.ColorUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class AnvilListener implements Listener
{
    private final JustRune plugin;
    private final RuneManager runeManager;
    private final NamespacedKey runeKey = new NamespacedKey("justrune", "rune-ids");

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event)
    {
        ItemStack first = event.getInventory().getItem(0);
        ItemStack second = event.getInventory().getItem(1);

        if (first == null || first.getType() != Material.TOTEM_OF_UNDYING)
            return;
        if (second == null || second.getType() == Material.AIR)
            return;

        ItemMeta firstMeta = first.getItemMeta();
        ItemMeta secondMeta = second.getItemMeta();
        if (firstMeta == null || secondMeta == null)
            return;

        List<String> currentRunes = getRuneIds(firstMeta);
        List<String> secondRunes = getRuneIds(secondMeta);

        if (!secondRunes.isEmpty())
        {
            if (second.getAmount() != 1)
                return;
            String newRuneId = secondRunes.get(0);
            if (currentRunes.contains(newRuneId))
                return;
            currentRunes.add(newRuneId);
        }
        else
        {
            if (currentRunes.isEmpty())
                return;
        }

        ItemStack result = event.getResult();
        if (result == null || result.getType() == Material.AIR)
            result = first.clone();

        ItemMeta meta = result.getItemMeta();
        if (meta == null)
            return;

        meta.getPersistentDataContainer().set(runeKey, PersistentDataType.STRING, String.join(",", currentRunes));

        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        cleanRuneLore(lore);

        lore.add(ColorUtils.color(""));
        lore.add(ColorUtils.color("&x&4&0&8&B&F&BЭффекты при активации:"));

        for (String runeId : currentRunes)
        {
            AbstractRune foundRune = runeManager.getRuneById(runeId);
            if (foundRune != null)
                lore.add(foundRune.getEffectDisplay());
        }

        lore.add(ColorUtils.color(" "));
        lore.add(ColorUtils.color("&7Применятся после использования"));

        meta.setLore(lore);
        result.setItemMeta(meta);

        event.getInventory().setRepairCost(10);
        event.setResult(result);
    }

    private List<String> getRuneIds(ItemMeta meta)
    {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        NamespacedKey oldKey = new NamespacedKey(plugin, "rune-id");
        if (pdc.has(oldKey, PersistentDataType.STRING))
        {
            String oldId = pdc.get(oldKey, PersistentDataType.STRING);
            if (oldId != null && !oldId.isEmpty())
                return new ArrayList<>(List.of(oldId));
        }

        if (pdc.has(runeKey, PersistentDataType.STRING))
        {
            String raw = pdc.get(runeKey, PersistentDataType.STRING);
            if (raw != null && !raw.isEmpty())
                return new ArrayList<>(Arrays.asList(raw.split(",")));
        }
        return new ArrayList<>();
    }

    private void cleanRuneLore(List<String> lore)
    {
        if (lore == null || lore.isEmpty())
            return;

        int startIndex = -1;
        for (int i = 0; i < lore.size(); i++)
        {
            String line = ChatColor.stripColor(lore.get(i));
            if (line.contains("Эффекты при активации:"))
            {
                startIndex = i;
                break;
            }
        }

        if (startIndex != -1)
        {
            if (startIndex > 0 && ChatColor.stripColor(lore.get(startIndex - 1)).trim().isEmpty())
                startIndex--;

            int endIndex = startIndex;
            for (int i = startIndex; i < lore.size(); i++)
            {
                String line = ChatColor.stripColor(lore.get(i));
                if (line.contains("Применятся после использования"))
                {
                    endIndex = i;
                    break;
                }
            }

            if (endIndex < lore.size())
                lore.subList(startIndex, endIndex + 1).clear();
        }
    }
}
