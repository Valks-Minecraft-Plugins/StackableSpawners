package com.stackablespawners.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
	public static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static ItemStack item(Material mat, String name, String[] theLore) {
		ItemStack item = new ItemStack(mat);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		
		if (theLore != null) {
			List<String> lore = new ArrayList<String>();
			for (String element : theLore) {
				lore.add(ChatColor.translateAlternateColorCodes('&', element));
			}
			im.setLore(lore);
		}
		
		item.setItemMeta(im);
		return item;
	}
}
