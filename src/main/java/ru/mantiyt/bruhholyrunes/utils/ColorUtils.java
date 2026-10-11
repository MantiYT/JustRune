package ru.mantiyt.bruhholyrunes.utils;

import net.md_5.bungee.api.ChatColor;

public class ColorUtils
{
    public static String color(String text)
    {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
