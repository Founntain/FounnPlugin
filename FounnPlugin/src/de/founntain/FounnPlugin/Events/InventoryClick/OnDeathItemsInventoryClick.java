package de.founntain.FounnPlugin.Events.InventoryClick;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.Utilities.DeathItems;
import net.md_5.bungee.api.ChatColor;

public class OnDeathItemsInventoryClick implements Listener{
	@EventHandler
	public void onServerMenuClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if(player == null) return;
		
		if(!e.getView().getTitle().equals(player.getDisplayName() + " DeathItems")) return;
		
		this.handleInventory(e, player);
		e.setCancelled(true);
	}
	
	private void handleInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem().getType() == Material.CHEST) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Alles nehmen")) {
				for(ItemStack item : e.getInventory().getContents()) {
					if(item == null)
						continue;
					
					if(item.getType() == Material.CHEST && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Alles nehmen")) 
						continue;		
					
					player.getInventory().addItem(item);
				}
				
				player.closeInventory();
				
				DeathItems.items.remove(player.getUniqueId());
			}
		}
	}
}
