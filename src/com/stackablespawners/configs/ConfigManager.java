package com.stackablespawners.configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.StackableSpawners;

public class ConfigManager {
	StackableSpawners plugin;
	
	File configFile;
	FileConfiguration config;
	
	public ConfigManager(String name) {
		plugin = JavaPlugin.getPlugin(StackableSpawners.class);
		configFile = new File(plugin.getDataFolder(), name + ".yml");
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
