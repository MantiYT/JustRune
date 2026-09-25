package org.zxc_tako25.justrune.rune.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.zxc_tako25.justrune.JustRune;
import org.zxc_tako25.justrune.rune.item.AbstractRune;
import org.zxc_tako25.justrune.rune.manager.RuneManager;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class TotemListener implements Listener
{
    private final JustRune plugin;
    private final RuneManager runeManager;

    @EventHandler
    public void onTotemActivate(EntityResurrectEvent event)
    {
        if (event.isCancelled() || !(event.getEntity() instanceof Player player))
            return;

        ItemStack usedTotem = getUsedTotem(player);
        if (usedTotem == null)
            return;

        ItemMeta totemMeta = usedTotem.getItemMeta();
        if (totemMeta == null)
            return;

        PersistentDataContainer pdc = totemMeta.getPersistentDataContainer();

        NamespacedKey multiKey = new NamespacedKey(plugin, "rune-ids");
        NamespacedKey singleKey = new NamespacedKey(plugin, "rune-id");

        List<String> runeIds = null;

        if (pdc.has(multiKey, PersistentDataType.STRING))
        {
            String raw = pdc.get(multiKey, PersistentDataType.STRING);
            if (raw != null && !raw.isEmpty())
                runeIds = Arrays.asList(raw.split(","));
        }
        else if (pdc.has(singleKey, PersistentDataType.STRING))
        {
            String singleId = pdc.get(singleKey, PersistentDataType.STRING);
            if (singleId != null)
                runeIds = List.of(singleId);
        }

        if (runeIds == null || runeIds.isEmpty())
            return;

        final List<String> finalRuneIds = runeIds;

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (!player.isOnline() || player.isDead())
                    return;

                for (String id : finalRuneIds)
                {
                    AbstractRune rune = runeManager.getRuneById(id);
                    if (rune != null)
                        rune.applyEffect(player);
                }
            }
        }.runTaskLater(plugin, 1L);
    }

    private ItemStack getUsedTotem(Player player)
    {
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();

        if (mainHand.getType() == Material.TOTEM_OF_UNDYING)
            return mainHand;
        if (offHand.getType() == Material.TOTEM_OF_UNDYING)
            return offHand;
        return null;
    }
}
