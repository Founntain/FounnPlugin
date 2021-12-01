package dev.founntain.founnplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.founntain.founnplugin.classes.Utilities;

public class OnPlayerJoinEvent implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		
		Player player = e.getPlayer();
		
		Bukkit.broadcastMessage("[" + ChatColor.GREEN + "+" + ChatColor.WHITE +"] "+ player.getDisplayName());
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setPlayerListHeader(Utilities.getTablistHeader());
			p.setPlayerListFooter(Utilities.getTablistFooter());
		}
	}
	
}
