package com.stackablespawners.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.StackableSpawners;
import com.stackablespawners.configs.ConfigManager;

public class SpawnerClicked implements Listener {
	StackableSpawners plugin;
	
	public SpawnerClicked() {
		plugin = JavaPlugin.getPlugin(StackableSpawners.class);
	}
	
	@EventHandler
	private void blockClicked(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		Location loc = b.getLocation();
		
		if (b.getType() != Material.MOB_SPAWNER) return;
		if (e.getHand() != EquipmentSlot.HAND) return;
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		
		System.out.println(loc);
		
		ConfigManager cm = plugin.spawnerConfig;
		Configuration config = cm.getConfig();
		ConfigurationSection configSection = config.getConfigurationSection("spawners");
		
		for (String element : configSection.getKeys(false)) {
			String path = "spawners." + element + ".loc.";
			
			System.out.println("===");
			System.out.println(element);
			System.out.println(config.getInt(path + "x"));
			System.out.println(config.getInt(path + "y"));
			System.out.println(config.getInt(path + "z"));
			System.out.println("===");
			
			if (config.getInt(path + "x") == loc.getBlockX() && config.getInt(path + "y") == loc.getBlockY() && config.getInt(path + "z") == loc.getBlockY()) {
				System.out.println("Works!");
				e.getPlayer().sendMessage("Spawner exists!");
			}
		}
	}
}
