package com.stackablespawners.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.stackablespawners.StackableSpawners;

public class SpawnerClicked implements Listener {
	@EventHandler
	private void blockClicked(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		Location loc = b.getLocation();
		
		if (b.getType() != Material.MOB_SPAWNER) return;
		
		Configuration config = StackableSpawners.spawnerConfig.getConfig();
		ConfigurationSection configSection = config.getConfigurationSection("spawners");
		
		for (String element : configSection.getKeys(false)) {
			String path = "spawners." + element + ".";
			if (config.getInt(path + "x") == loc.getBlockX() && config.getInt(path + "y") == loc.getBlockY() && config.getInt(path + "z") == loc.getBlockY()) {
				e.getPlayer().sendMessage("Spawner exists!");
				break;
			}
		}
	}
}
