package me.jessicahere.jessicaproof.bungeecord.settings;

import me.jessicahere.jessicaproof.bungeecord.utils.ConfigUtils;

public class Settings {

	
	public static String botToken = ConfigUtils.getInstance().getConfig().getString("Bot-Token");
	public static String textChannelId = ConfigUtils.getInstance().getConfig().getString("TextChannel-ID");
	public static String guildId = ConfigUtils.getInstance().getConfig().getString("Guild-ID");
	public static int delayTime = ConfigUtils.getInstance().getConfig().getInt("delay");
	
}
