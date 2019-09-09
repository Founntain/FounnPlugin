package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import net.md_5.bungee.api.ChatColor;

public class OnPlayerChangedWorldEvent implements Listener{
	@EventHandler
	public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent e) {	
		Player player = e.getPlayer();
			
		this.broadcastWorldChangedMessage(
			this.getChatColorFromEnvironment(e.getFrom().getEnvironment()), 
			this.getChatColorFromEnvironment(player.getWorld().getEnvironment()),
			this.getFormatedEnvironmentName(e.getFrom().getEnvironment()),
			this.getFormatedEnvironmentName(player.getWorld().getEnvironment()),
			player
		);
	}
	
	private ChatColor getChatColorFromEnvironment(Environment environment) {
		switch (environment) {
		case NETHER:
			return ChatColor.LIGHT_PURPLE;
		case THE_END:
			return ChatColor.DARK_PURPLE;
		default:
			return ChatColor.GREEN;
		}
	}
	
	private String getFormatedEnvironmentName(Environment environment) {
		switch (environment) {
		case NETHER:
			return "Nether";
		case THE_END:
			return "The End";
		default:
			return "Overworld";
		}
	}
	
	private void broadcastWorldChangedMessage(ChatColor colorFrom, ChatColor colorTo, String fromWorld, String toWorld, Player player) {
		Bukkit.broadcastMessage("[" + ChatColor.BLUE + "W" + ChatColor.WHITE + "] " + player.getDisplayName() + " | " 
				+ colorFrom + fromWorld + ChatColor.WHITE + " => " + colorTo + toWorld);
	}
}
