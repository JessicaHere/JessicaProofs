package me.jessicahere.jessicaproof.bungeecord.utils;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigUtils {

	private static ConfigUtils instance;
	private File configFile;
	private Configuration config;

	public static ConfigUtils getInstance() {
		if (instance == null) {
			instance = new ConfigUtils();
		}
		return instance;
	}

	public void initialize(File dataFolder) {
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}
		this.configFile = new File(dataFolder, "config.yml");
	}

	public void createConfig() {
		if (!configFile.exists()) {
			try (InputStream in = getResourceAsStream("config.yml")) {
				Files.copy(in, configFile.toPath());
				getConfig().set("Bot-Token", (String) "YourBotToken");
				getConfig().set("delay", (int) 1);
				getConfig().set("Guild-ID", (String) "YourServerID");
				getConfig().set("TextChannel-ID", (String) "YourChannelID");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Configuration getConfig() {
		if (config == null) {
			try {
				config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config;
	}

	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void reloadConfig() {
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private InputStream getResourceAsStream(String fileName) {
		return getClass().getClassLoader().getResourceAsStream(fileName);
	}

}
