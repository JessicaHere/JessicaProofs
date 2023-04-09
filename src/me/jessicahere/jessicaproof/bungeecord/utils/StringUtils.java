package me.jessicahere.jessicaproof.bungeecord.utils;

import net.md_5.bungee.api.ChatColor;

public class StringUtils {

	public static String colorize(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}
