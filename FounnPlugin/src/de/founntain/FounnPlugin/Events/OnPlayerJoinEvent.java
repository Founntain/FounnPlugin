package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;

public class OnPlayerJoinEvent implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		
		Player player = e.getPlayer();
		
		Bukkit.broadcastMessage("[" + ChatColor.GREEN + "+" + ChatColor.WHITE +"] "+ player.getDisplayName());

	}
	
}
