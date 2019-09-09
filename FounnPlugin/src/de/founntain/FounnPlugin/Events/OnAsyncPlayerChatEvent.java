package de.founntain.FounnPlugin.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

public class OnAsyncPlayerChatEvent implements Listener{
	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		String format = "";
		
		Player player = e.getPlayer();
		
		if(player.getDisplayName().equals("Founntain")) {
			format = "[" + ChatColor.DARK_PURPLE + "Owner" + ChatColor.WHITE + "] %s >> " + ChatColor.GOLD + "%s";
		}else {
			format = "[" + ChatColor.YELLOW + "Spieler" + ChatColor.WHITE + "] %s >> " + ChatColor.WHITE + "%s";
		}
		
		e.setFormat(format);
	}
}
