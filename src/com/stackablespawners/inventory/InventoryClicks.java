package com.stackablespawners.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClicks implements Listener {
	@EventHandler
	private void registerInvClicks(InventoryClickEvent e) {
		if (e.getView().getTitle().equals("Spawner")) {
			e.setCancelled(true);
			switch (e.getSlot()) {
			case 0: // Level
				break;
			case 1: // Type
				break;
			}
		}
	}
}
