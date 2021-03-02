package de.founntain.founnplugin.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.founntain.founnplugin.classes.BedMap;
import de.founntain.founnplugin.classes.Utilities;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerQuitEvent implements Listener{

	@EventHandler
	public void OnPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		
		Player player = e.getPlayer();
		
		BedMap.removeIfExists(player.getUniqueId());
		
		Bukkit.broadcastMessage("[" + ChatColor.RED + "-" + ChatColor.WHITE +"] "+ player.getDisplayName());
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setPlayerListHeader(Utilities.getTablistHeader());
			p.setPlayerListFooter(Utilities.getTablistFooter());
		}
	}
}
