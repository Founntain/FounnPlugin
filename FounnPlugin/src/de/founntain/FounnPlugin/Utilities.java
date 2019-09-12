package de.founntain.FounnPlugin;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Utilities {
	public static String getFormatedEnvironmentName(Environment environment) {
		switch (environment) {
		case NETHER:
			return ChatColor.LIGHT_PURPLE + "Nether";
		case THE_END:
			return ChatColor.DARK_PURPLE + "The End";
		default:
			return ChatColor.GREEN + "Overworld";
		}
	}
	
	public static int getEmptyInventorySlotsCount(Inventory inventory) {
		int i = 0;
		for(ItemStack is : inventory.getContents()) {
			if(is != null)
				continue;
			
			i++;
		}
		
		return i;
	}
	
	public static int getUsedInventorySlotsCount(Inventory inventory) {
		int i = 0;
		for(ItemStack is : inventory.getContents()) {
			if(is == null)
				continue;
			i++;
		}
		
		return i;
	}
	
	public static String getErrorPrefix() {
		return ChatColor.WHITE + "[" + ChatColor.RED + "E" + ChatColor.WHITE +"] ";
	}
	
	public static String getCustomPrefix(ChatColor letterColor, String letter) {
		return ChatColor.WHITE + "[" + letterColor + letter.toUpperCase() + ChatColor.WHITE +"] ";
	}
	
	public static ItemStack createMenuItem(Material material, String label) {
		ItemStack item = new ItemStack(material);
		
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(label);
		
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack createMenuItem(Material material, String label, String[] description) {
		ItemStack item = new ItemStack(material);
		
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(label);
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		for(String s : description)
			arrayList.add(s);
		
		itemMeta.setLore(arrayList);
		
		item.setItemMeta(itemMeta);
		
		return item;
	}
}
