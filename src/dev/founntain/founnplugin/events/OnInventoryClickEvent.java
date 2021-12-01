package dev.founntain.founnplugin.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnInventoryClickEvent implements Listener{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {		
		Player player = (Player) e.getWhoClicked();

		if(player == null)
			return;
		
		if(e.getView().getTitle().equals(ChatColor.DARK_PURPLE + player.getDisplayName() + "s Statistik")) {
			e.setCancelled(true);
			return;
		}
		
		if(e.getView().getTitle().equals(ChatColor.GOLD + "Chunkscan result")) {
			e.setCancelled(true);
			return;
		}
	}
}
