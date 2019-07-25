package com.stackablespawners;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.events.SpawnerPlaced;

public class StackableSpawners extends JavaPlugin {
	// 1.12.2 Plugin
	
	File spawnerConfigFile = new File(getDataFolder(), "spawners.yml");
	FileConfiguration spawnerConfig = YamlConfiguration.loadConfiguration(spawnerConfigFile);
	
	@Override
	public void onEnable() {
		registerEvents(getServer().getPluginManager());
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new SpawnerPlaced(), this);
	}
	
	public Configuration getSpawnerConfig() {
		return spawnerConfig;
	}

	public void saveSpawnerConfig() {
		try {
			spawnerConfig.save(spawnerConfigFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
