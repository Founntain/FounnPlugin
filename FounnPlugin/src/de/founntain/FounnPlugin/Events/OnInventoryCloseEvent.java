package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.Utilities;
import net.md_5.bungee.api.ChatColor;

public class OnInventoryCloseEvent implements Listener{
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		
		if(e.getView().getTitle().contains("Receiver: ")) {
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
		}
	}
}
