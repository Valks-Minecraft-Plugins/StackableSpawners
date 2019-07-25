package com.stackablespawners.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.StackableSpawners;
import com.stackablespawners.configs.ConfigManager;

public class SpawnerDestroyed implements Listener {
	StackableSpawners plugin;
	
	public SpawnerDestroyed() {
		plugin = JavaPlugin.getPlugin(StackableSpawners.class);
	}
	
	@EventHandler
	private void blockDestroyed(BlockBreakEvent e) {
		if (e.getBlock().getType() != Material.MOB_SPAWNER) return;
		
		Location loc = e.getBlock().getLocation();
		
		ConfigManager cm = plugin.spawnerConfig;
		Configuration config = cm.getConfig();
		ConfigurationSection configSection = config.getConfigurationSection("spawners");
		
		if (!config.isConfigurationSection("spawners")) return;
		
		for (String element : configSection.getKeys(false)) {
			String pathLoc = "spawners." + element + ".loc.";
			
			if (config.getInt(pathLoc + "x") == loc.getBlockX() && config.getInt(pathLoc + "y") == loc.getBlockY() && config.getInt(pathLoc + "z") == loc.getBlockZ()) {
				config.set("spawners." + element, null);
				cm.saveConfig();
			}
		}
	}
}
