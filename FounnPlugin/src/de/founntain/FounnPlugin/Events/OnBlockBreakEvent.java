package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import net.md_5.bungee.api.ChatColor;

public class OnBlockBreakEvent implements Listener{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block block = e.getBlock();
		Player player = e.getPlayer();
		
		switch(block.getType()) {
			case DIAMOND_ORE:
				Bukkit.broadcastMessage(ChatColor.AQUA + player.getDisplayName() + " hat ein Diamant gefunden!");
				break;
			case EMERALD_ORE:
				Bukkit.broadcastMessage(ChatColor.GREEN + player.getDisplayName() + " hat ein Smaragd gefunden!"); 
				break;
			case ANCIENT_DEBRIS:
				Bukkit.broadcastMessage(ChatColor.DARK_GRAY + player.getDisplayName() + " hat Ancient Debris gefunden!");
		default:
			return; 
		}	
	}
}
