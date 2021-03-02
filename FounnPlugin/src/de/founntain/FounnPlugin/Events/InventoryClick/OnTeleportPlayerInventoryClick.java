package de.founntain.FounnPlugin.Events.InventoryClick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class OnTeleportPlayerInventoryClick implements Listener{
	@EventHandler
	public void onServerMenuClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if(player == null) return;
		
		if(!e.getView().getTitle().startsWith(ChatColor.DARK_PURPLE+ "Adminwerkzeuge")) return;
		
		this.handleInventory(e, player);
		e.setCancelled(true);
	}
	
	private void handleInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType() != Material.PLAYER_HEAD) return;
		
		ItemStack item = e.getCurrentItem();
		
		Player clickedPlayer = Bukkit.getPlayerExact(item.getItemMeta().getDisplayName().replace(ChatColor.YELLOW + "", ""));
		
		if(e.getView().getTitle().endsWith("to specific player")) 
			player.teleport(clickedPlayer.getLocation());
		else if(e.getView().getTitle().endsWith("specific player to you")) 
			clickedPlayer.teleport(player.getLocation());
		
		e.setCancelled(true);
		
		player.closeInventory();
	}
}
