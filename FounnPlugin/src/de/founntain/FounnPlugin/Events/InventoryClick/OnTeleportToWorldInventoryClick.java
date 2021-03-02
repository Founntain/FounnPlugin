package de.founntain.FounnPlugin.Events.InventoryClick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class OnTeleportToWorldInventoryClick implements Listener{
	@EventHandler
	public void onServerMenuClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if(player == null) return;
		
		if(!e.getView().getTitle().equals(ChatColor.DARK_GREEN + "World list")) return;
		
		this.handleInventory(e, player);
		e.setCancelled(true);
	}
	
	private void handleInventory(InventoryClickEvent e, Player player) {
		ItemStack itemClicked = e.getCurrentItem();
		
		if(itemClicked == null || itemClicked.getType() == Material.AIR) return;
		
		String worldname = itemClicked.getItemMeta().getDisplayName();
		
		for(World world : Bukkit.getServer().getWorlds()) {
			if(!(ChatColor.YELLOW + world.getName()).equals(worldname)) continue;
			
			player.teleport(world.getSpawnLocation());
			
			e.setCancelled(true);
			return;
		}
	}
}
