package com.stackablespawners.configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	File configFile;
	FileConfiguration config;
	
	public ConfigManager(File dataFolder, String name) {
		configFile = new File(dataFolder, name + ".yml");
		config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public Configuration getConfig() {
		return config;
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
