package com.stackablespawners.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.stackablespawners.StackableSpawners;
import com.stackablespawners.configs.ConfigManager;
import com.stackablespawners.utils.Utils;

public class SpawnerClicked implements Listener {
	StackableSpawners plugin;
	
	public SpawnerClicked() {
		plugin = JavaPlugin.getPlugin(StackableSpawners.class);
	}
	
	@EventHandler
	private void blockClicked(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		
		if (b == null) return;
		
		Location loc = b.getLocation();
		
		if (b.getType() != Material.MOB_SPAWNER) return;
		if (e.getHand() != EquipmentSlot.HAND) return;
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		
		ConfigManager cmSpawner = plugin.spawnerConfig;
		Configuration spawnerConfig = cmSpawner.getConfig();
		ConfigurationSection configSection = spawnerConfig.getConfigurationSection("spawners");
		
		ConfigManager cmMain = plugin.mainConfig;
		Configuration mainConfig = cmMain.getConfig();
		
		if (!spawnerConfig.isConfigurationSection("spawners")) return;
		
		for (String element : configSection.getKeys(false)) {
			String pathLoc = "spawners." + element + ".loc.";
			
			if (spawnerConfig.getInt(pathLoc + "x") == loc.getBlockX() && spawnerConfig.getInt(pathLoc + "y") == loc.getBlockY() && spawnerConfig.getInt(pathLoc + "z") == loc.getBlockZ()) {
				Player p = e.getPlayer();
				ItemStack spawner = p.getEquipment().getItemInMainHand();
				
				String pathLevel = "spawners." + element + ".level";
				int lvl = spawnerConfig.getInt(pathLevel);
				
				if (spawner.getType() == Material.MOB_SPAWNER) {
					e.setCancelled(true);
					spawner.setAmount(spawner.getAmount() - 1);
					
					int newLvl = lvl + 1;
					int maxLvl = mainConfig.getInt("maxSpawnerLevel");
					
					if (maxLvl == -1) {
						maxLvl = 3;
					}
					
					if (newLvl > maxLvl) {
						p.sendMessage(Utils.color("&7The spawner can't exceed the max level (&f" + lvl + "&7)!"));
						return;
					}
					
					spawnerConfig.set(pathLevel, newLvl);
					p.sendMessage(Utils.color("&7Spawner upgraded to level &f" + newLvl + "&7."));
					cmSpawner.saveConfig();
				} else {
					Inventory inv = Bukkit.createInventory(null, 9, "Spawner");
					inv.setItem(0, Utils.item(Material.FEATHER, "&7Lvl", new String[] {"&f" + lvl}));
					
					CreatureSpawner type = (CreatureSpawner) b.getState();
					EntityType mobType = type.getSpawnedType();
					
					String mobName = mobType.name();
					inv.setItem(1, Utils.item(Material.MONSTER_EGG, "&7Type", new String[] {"&f" + mobName.substring(0, 1).toUpperCase() + mobName.substring(1).toLowerCase()}));
					p.openInventory(inv);
				}
			}
		}
	}
}
