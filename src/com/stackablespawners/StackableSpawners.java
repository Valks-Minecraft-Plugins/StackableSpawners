package com.stackablespawners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.configs.ConfigManager;
import com.stackablespawners.events.SpawnerPlaced;

public class StackableSpawners extends JavaPlugin {
	// 1.12.2
	public static ConfigManager mainConfig = new ConfigManager("config");
	public static ConfigManager spawnerConfig = new ConfigManager("spawners");
	
	@Override
	public void onEnable() {
		registerEvents(getServer().getPluginManager());
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new SpawnerPlaced(), this);
	}
}
