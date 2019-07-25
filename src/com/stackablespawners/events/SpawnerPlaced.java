package com.stackablespawners.events;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.StackableSpawners;
import com.stackablespawners.configs.ConfigManager;

public class SpawnerPlaced implements Listener {
	StackableSpawners plugin;
	
	public SpawnerPlaced() {
		plugin = JavaPlugin.getPlugin(StackableSpawners.class);
	}
	
	@EventHandler
	private void blockPlaced(BlockPlaceEvent e) {
		if (!e.getPlayer().hasPermission("stackablespawners.use") && !plugin.mainConfig.getConfig().getBoolean("ignorePermissions")) return;
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE && plugin.mainConfig.getConfig().getBoolean("ignoreCreativeMode")) return;
		if (e.getBlock().getType() != Material.MOB_SPAWNER) return;
		
		ConfigManager cm = plugin.spawnerConfig;
		Configuration config = cm.getConfig();
		
		Block b = e.getBlock();
		CreatureSpawner type = (CreatureSpawner) b.getState();
		EntityType mobType = type.getSpawnedType();
		Location loc = b.getLocation();
		
		ConfigurationSection sectionSpawners = config.getConfigurationSection("spawners");
		
		int slot = 1;
		
		if (config.isConfigurationSection("spawners")) {
			for (String element : sectionSpawners.getKeys(false)) {
				if (element != null) {
					slot++;
				}
			}
		}
		
		config.set("spawners." + slot + ".loc.x", loc.getBlockX());
		config.set("spawners." + slot + ".loc.y", loc.getBlockY());
		config.set("spawners." + slot + ".loc.z", loc.getBlockZ());
		config.set("spawners." + slot + ".type", mobType.name());
		config.set("spawners." + slot + ".level", 1);
		cm.saveConfig();
	}
}
