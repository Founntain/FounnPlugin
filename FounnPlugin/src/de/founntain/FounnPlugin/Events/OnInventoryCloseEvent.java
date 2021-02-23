package de.founntain.FounnPlugin.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.founntain.FounnPlugin.DeathItems;
import de.founntain.FounnPlugin.Pair;
import de.founntain.FounnPlugin.Utilities;
import net.md_5.bungee.api.ChatColor;

public class OnInventoryCloseEvent implements Listener{
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		
		if(e.getView().getTitle().contains("Receiver: ")) {
			this.sendItem(e, player);
			return;
		}
		
		if(e.getView().getTitle().equals(player.getDisplayName() + " DeathItems")) {
			this.deathBox(e, player);
			return;
		}
		
		if(e.getView().getTitle().contains("Verzaubere Item")) {
			this.enchantItem(e, player);
			return;
		}
	}
	
	private void sendItem(InventoryCloseEvent e, Player player) {
		Inventory guiInventory = e.getInventory();
		
		if(Utilities.getUsedInventorySlotsCount(e.getInventory()) == 0) {
			player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + "Du hast keine Items zum senden übergeben, der Vorgang wurde abgebrochen!");
			return;
		}
		
		ItemStack[] items = guiInventory.getContents();
		
		Player receiver = Bukkit.getPlayer(e.getView().getTitle().split(": ")[1]);
		
		if(Utilities.getUsedInventorySlotsCount(guiInventory) > Utilities.getEmptyInventorySlotsCount(receiver.getInventory()) - 5) {
			player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + receiver.getDisplayName() + " hat nicht genügend Slots in seinem Inventar frei!");
			
			for(ItemStack item : items) {
				if(item == null)
					continue;
				
				player.getInventory().addItem(item);
			}
			
			return;
		}
		
		for(ItemStack item : items) {
			if(item == null)
				continue;
			
			receiver.getInventory().addItem(item);
		}	
		player.sendMessage(Utilities.getCustomPrefix(ChatColor.YELLOW, "I") + ChatColor.GREEN + receiver.getDisplayName() + " hat erfolgreich deine Items erhalten!");
		receiver.sendMessage(Utilities.getCustomPrefix(ChatColor.YELLOW, "I") + ChatColor.GREEN + player.getDisplayName() + " hat dir Items geschickt!");
		
	}
	
	private void deathBox(InventoryCloseEvent e, Player player) {
		DeathItems.items.put(player.getUniqueId(), e.getInventory().getContents());
	}
	
	private void enchantItem(InventoryCloseEvent e, Player player) {
		ItemStack[] items = e.getInventory().getContents();
		
		ItemStack tool = null;
		ItemStack book = null;
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
			
			return;
		}
		
		for(ItemStack item : items) {
			if(item == null || item.getType() == Material.AIR)
				continue;
			
			if(item.getType() == Material.ENCHANTED_BOOK) {
				book = item;
				continue;
			}
			
			tool = item;
		}
		
		ArrayList<Pair<Enchantment, Integer>> enchantments = Utilities.GetEnchantmentsFromItem(book);
		
		if(enchantments.size() == 0) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Auf dem Buch ist keine Verzauberung vorhanden!");
			
			player.getInventory().addItem(tool);
			player.getInventory().addItem(book);
			
			return;
		}
		
		if(enchantments.size() != 1) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Auf dem Buch darf nur EINE Verzauberung sein!");
			
			player.getInventory().addItem(tool);
			player.getInventory().addItem(book);
			
			return;
		}
		
		for(Pair<Enchantment, Integer> enchantment : enchantments) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Enchantment: " + enchantment.GetItem1().getKey() + " | Level " + enchantment.GetItem2());
		}
		
		Pair<Enchantment, Integer> ench = enchantments.get(0);
		
		if(!ench.GetItem1().canEnchantItem(tool)) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Diese Verzauberung kann nicht auf dieses Item angewendet werden!");
			
			player.getInventory().addItem(tool);
			player.getInventory().addItem(book);
			
			return;
		}
		
		ItemMeta meta = tool.getItemMeta();
		meta.addEnchant(ench.GetItem1(), ench.GetItem2(), true);
		
		tool.setItemMeta(meta);
		
		player.getInventory().addItem(tool);
		
		player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Item Verzaubert und Buch konsumiert!");
		
		return;
	}
}
