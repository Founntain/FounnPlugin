package de.founntain.FounnPlugin;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DeathItems {
	public static HashMap<UUID, ItemStack[]> Items;
	
	public static void showDeathItems(UUID playerId) {
		Player player = Bukkit.getPlayer(playerId);
		Inventory deathItems = Bukkit.createInventory(player, 54, player.getDisplayName() + " DeathItems");
		
		ItemStack[] itemsFromPlayer = Items.get(playerId);
		
		for(ItemStack item : itemsFromPlayer) {
			if(item == null) 
				continue;
			
			deathItems.addItem(item);
		}
		
		deathItems.setItem(deathItems.getSize() - 1, Utilities.createMenuItem(Material.CHEST, ChatColor.DARK_PURPLE + "Alles nehmen"));
		
		player.openInventory(deathItems);
	}
}
