package de.founntain.FounnPlugin.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class OnBlockPlaceEvent implements Listener{
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		ItemStack block = e.getItemInHand();
		
		int newAmount = block.getAmount() - 1;
		
		if(newAmount > 0) return;
		
		PlayerInventory inventory = e.getPlayer().getInventory();
		ItemStack[] items = inventory.getContents();
		
		for(int i = 0; i < items.length; i++) {
			ItemStack item = items[i];
			
			if(item == null || item.getType() == Material.AIR) continue;
			if(item.equals(block)) continue;
			if(item.getType() != block.getType()) continue;
			
			ItemStack clone = item.clone();
			
			inventory.setItemInMainHand(clone);
			inventory.clear(i);
			return;
		}
	}
}
