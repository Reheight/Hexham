package me.reheight.hexham.utils;

import org.bukkit.ChatColor;

import me.reheight.hexham.strings.Languages;

public class Color {
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', Languages.prefix + " " + s);
	}
	
	public static String format(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
