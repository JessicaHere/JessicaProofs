package me.jessicahere.jessicaproof.bungeecord.core;

import me.jessicahere.jessicaproof.bungeecord.commands.ProofCommand;
import me.jessicahere.jessicaproof.bungeecord.commands.ReloadCommand;
import me.jessicahere.jessicaproof.bungeecord.settings.Settings;
import me.jessicahere.jessicaproof.bungeecord.utils.ConfigUtils;
import me.jessicahere.jessicaproof.jda.BotClass;
import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin {

	public static BotClass myBot;

	@Override
	public void onEnable() {
		ConfigUtils.getInstance().initialize(this.getDataFolder());
		ConfigUtils.getInstance().createConfig();

		myBot = new BotClass(Settings.botToken);
		myBot.build();

		getProxy().getPluginManager().registerCommand(this, new ProofCommand());
		getProxy().getPluginManager().registerCommand(this, new ReloadCommand());

		getLogger().severe("Plugin has been enabled successfully ! \n Developer : JessicaHere");
		ConfigUtils.getInstance().saveConfig();
	}

	@Override
	public void onDisable() {
		getLogger().severe("Plugin has been disabled successfully ! \n Developer : JessicaHere");
	}

	public static BotClass getBot() {
		return myBot;
	}

}
