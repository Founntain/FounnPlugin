package de.founntain.founnplugin.classes;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantHelper {
	public static boolean enchantValididation(ItemStack[] items, Player player) {
		int itemCount = 0;
		
		for(ItemStack item : items) {
			if(item == null || item.getType() == Material.AIR)
				continue;
			
			itemCount++;
		}
		
		if(itemCount != 2) {
			for(ItemStack item : items) {
				if(item == null || item.getType() == Material.AIR)
					continue;
				
				player.getInventory().addItem(item);
			}
			
			player.sendMessage(Utilities.getErrorPrefix() + "Du kannst nur EIN Item und nur EIN Buch benutzten!");
			
			return false;
		}
		
		return true;
	}
	
	public static boolean enchantBookItemValidation(ArrayList<Pair<Enchantment, Integer>> enchantments, Player player, ItemStack tool, ItemStack book) {
		if(enchantments.size() == 0) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Auf dem Buch ist keine Verzauberung vorhanden!");
			
			player.getInventory().addItem(tool);
			player.getInventory().addItem(book);
			
			return false;
		}
		
		if(enchantments.size() != 1) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Auf dem Buch darf nur EINE Verzauberung sein!");
			
			player.getInventory().addItem(tool);
			player.getInventory().addItem(book);
			
			return false;
		}
		
		return true;
	}
	
	public static boolean enchantItem(ArrayList<Pair<Enchantment, Integer>> enchantments, Player player, ItemStack tool, ItemStack book) {
Pair<Enchantment, Integer> ench = enchantments.get(0);
		
		if(!ench.GetItem1().canEnchantItem(tool)) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Diese Verzauberung kann nicht auf dieses Item angewendet werden!");
			
			player.getInventory().addItem(tool);
			player.getInventory().addItem(book);
			
			return false;
		}
		
		ItemMeta meta = tool.getItemMeta();
		meta.addEnchant(ench.GetItem1(), ench.GetItem2(), true);
		
		tool.setItemMeta(meta);
		
		player.getInventory().addItem(tool);
		
		player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Item Verzaubert und Buch konsumiert!");
		
		return true;
	}
}
