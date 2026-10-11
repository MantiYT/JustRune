package ru.mantiyt.bruhholyrunes.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemRune
{
    public ItemStack giveItem(AbstractItem rune, Integer amount)
    {
        ItemStack item = new ItemStack(rune.getMaterial(), amount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(rune.getDisplayName());
        itemMeta.addEnchant(Enchantment.LUCK, 1, true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        itemMeta.getPersistentDataContainer().set(
                rune.getRuneKey(),
                PersistentDataType.STRING,
                rune.getRuneId()
        );

        itemMeta.setLore(rune.getLore());
        item.setItemMeta(itemMeta);

        return item;
    }
}
