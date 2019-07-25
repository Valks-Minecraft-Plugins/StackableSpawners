package com.stackablespawners;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.configs.ConfigManager;
import com.stackablespawners.events.SpawnerClicked;
import com.stackablespawners.events.SpawnerPlaced;
import com.stackablespawners.inventory.InventoryClicks;

public class StackableSpawners extends JavaPlugin {
	// 1.12.2
	public ConfigManager mainConfig = new ConfigManager(getDataFolder(), "config");
	public ConfigManager spawnerConfig = new ConfigManager(getDataFolder(), "spawners");
	
	@Override
	public void onEnable() {
		registerEvents(getServer().getPluginManager());
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new SpawnerPlaced(), this);
		pm.registerEvents(new SpawnerClicked(), this);
		pm.registerEvents(new InventoryClicks(), this);
	}
}
