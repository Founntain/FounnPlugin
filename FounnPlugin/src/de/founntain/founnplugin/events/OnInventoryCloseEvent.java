package de.founntain.founnplugin.events;

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

import de.founntain.founnplugin.classes.DeathItems;
import de.founntain.founnplugin.classes.EnchantHelper;
import de.founntain.founnplugin.classes.Pair;
import de.founntain.founnplugin.classes.Utilities;
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
		
		if(!EnchantHelper.enchantValididation(items, player))
			return;
		
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
		
		if(!EnchantHelper.enchantBookItemValidation(enchantments, player, tool, book))
			return;
		
		for(Pair<Enchantment, Integer> enchantment : enchantments) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Z") + ChatColor.GOLD + "Enchantment: " + enchantment.GetItem1().getKey() + " | Level " + enchantment.GetItem2());
		}
		
		EnchantHelper.enchantItem(enchantments, player, tool, book);
	}
}
