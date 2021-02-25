package de.founntain.FounnPlugin.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class OnPlayerItemBreakEvent implements Listener{
	@EventHandler
	public void onPlayerItemBreakEvent(PlayerItemBreakEvent e) {
		
		Player player = e.getPlayer();
		PlayerInventory inventory = player.getInventory();
		ItemStack brokenItem = e.getBrokenItem();
		
		ItemStack[] items = inventory.getContents();
		
		for(int i = 0; i < items.length; i++) {
			ItemStack itemAtPos = items[i];
			
			if(itemAtPos == null || itemAtPos.getType() == Material.AIR) continue;
			if(itemAtPos.equals(brokenItem)) continue;
			if(itemAtPos.getType() != brokenItem.getType()) continue;
			
			ItemStack clone = itemAtPos.clone();
			
			inventory.setItemInMainHand(clone);
			inventory.clear(i);
			return;
		}
	}
}