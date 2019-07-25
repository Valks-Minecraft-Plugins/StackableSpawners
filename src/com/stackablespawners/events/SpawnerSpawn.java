package com.stackablespawners.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.StackableSpawners;
import com.stackablespawners.configs.ConfigManager;

public class SpawnerSpawn implements Listener {
	StackableSpawners plugin;
	
	public SpawnerSpawn() {
		plugin = JavaPlugin.getPlugin(StackableSpawners.class);
	}
	
	@EventHandler
	private void spawnerMobSpawn(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.SPAWNER) return;
		
		Location loc = e.getLocation();
		Location spawnerLoc = null;
		
		final int RADIUS = 11; // Radius must be odd number to account for middle.
		for (int x = -RADIUS / 2; x < RADIUS / 2 + 1; x++) {
			for (int y = -RADIUS / 2; y < RADIUS / 2 + 1; y++) {
				for (int z = -RADIUS / 2; z < RADIUS / 2 + 1; z++) {
					if (loc.clone().add(x, y, z).getBlock().getType() == Material.MOB_SPAWNER) {
						spawnerLoc = loc.clone().add(x, y, z);
					}
				}
			}
		}
		
		if (spawnerLoc == null) return;
		
		ConfigManager cm = plugin.spawnerConfig;
		Configuration config = cm.getConfig();
		ConfigurationSection configSection = config.getConfigurationSection("spawners");
		
		if (!config.isConfigurationSection("spawners")) return;
		
		for (String element : configSection.getKeys(false)) {
			String pathLoc = "spawners." + element + ".loc.";
			
			if (config.getInt(pathLoc + "x") == spawnerLoc.getBlockX() && config.getInt(pathLoc + "y") == spawnerLoc.getBlockY() && config.getInt(pathLoc + "z") == spawnerLoc.getBlockZ()) {
				e.setCancelled(true);
				int lvl = config.getInt("spawners." + element + ".level");
				// Bukkit.broadcastMessage("" + lvl);
				for (int i = 0; i < lvl; i++) {
					loc.getWorld().spawnEntity(loc, e.getEntityType());
				}
			}
		}
	}
}
