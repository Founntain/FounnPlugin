package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import de.founntain.FounnPlugin.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerChangedWorldEvent implements Listener{
	@EventHandler
	public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {	
		Player player = e.getPlayer();
		
		this.broadcastWorldChangedMessage(
			Utilities.getFormatedEnvironmentName(e.getFrom().getEnvironment()) + " (" + e.getFrom().getName() + ")",
			Utilities.getFormatedEnvironmentName(player.getWorld().getEnvironment()) + " (" + player.getWorld().getName() + ")",
			player
		);
	}
	
	private void broadcastWorldChangedMessage(String fromWorld, String toWorld, Player player) {
		Bukkit.broadcastMessage("[" + ChatColor.BLUE + "W" + ChatColor.WHITE + "] " + player.getDisplayName() + " | " 
				+ fromWorld + ChatColor.WHITE + " => " + toWorld);
	}
}
