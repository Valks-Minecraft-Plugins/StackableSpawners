package com.stackablespawners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.configs.ConfigManager;
import com.stackablespawners.events.SpawnerClicked;
import com.stackablespawners.events.SpawnerDestroyed;
import com.stackablespawners.events.SpawnerPlaced;
import com.stackablespawners.events.SpawnerSpawn;
import com.stackablespawners.inventory.InventoryClicks;

public class StackableSpawners extends JavaPlugin {
	// 1.12.2
	public ConfigManager mainConfig = new ConfigManager(getDataFolder(), "config");
	public ConfigManager spawnerConfig = new ConfigManager(getDataFolder(), "spawners");
	
	@Override
	public void onEnable() {
		registerEvents(getServer().getPluginManager());
		initSpawnerConfig();
		initMainConfig();
	}
	
	private void initMainConfig() {
		Configuration config = mainConfig.getConfig();
		
		if (!config.isSet("maxSpawnerLevel")) {
			config.set("maxSpawnerLevel", 3);
		}
		
		if (!config.isSet("ignoreCreativeMode")) {
			config.set("ignoreCreativeMode", false);
		}
		
		if (!config.isSet("ignorePermissions")) {
			config.set("ignorePermissions", true);
		}
		
		mainConfig.saveConfig();
	}
	
	private void initSpawnerConfig() {
		Configuration config = spawnerConfig.getConfig();
		
		if (!config.isSet("spawners.1")) {
			List<String> list = new ArrayList<String>();
			config.set("spawners", list);
			spawnerConfig.saveConfig();
		}
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new SpawnerPlaced(), this);
		pm.registerEvents(new SpawnerClicked(), this);
		pm.registerEvents(new InventoryClicks(), this);
		pm.registerEvents(new SpawnerDestroyed(), this);
		pm.registerEvents(new SpawnerSpawn(), this);
	}
}
