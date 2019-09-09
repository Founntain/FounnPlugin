package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
		
		if(block.getType() == Material.DIAMOND_ORE || block.getType() == Material.EMERALD_ORE) {
			if(block.getType() == Material.DIAMOND_ORE) {
				Bukkit.broadcastMessage(ChatColor.AQUA + player.getDisplayName() + " hat Diamanten gefunden!");
			}else {
				Bukkit.broadcastMessage(ChatColor.GREEN + player.getDisplayName() + " hat Smaragde gefunden!");
			}
		} else if(block.getType().toString().toLowerCase().contains("log")) {
			player.setExp((float)(player.getExp() + 0.01));
		}
	}
}
