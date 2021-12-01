package dev.founntain.founnplugin.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import dev.founntain.founnplugin.classes.PlayerTeleportCoords;

public class OnPlayerTeleportEvent implements Listener{
	@EventHandler
	public void onPlayerTeleportEvent(PlayerTeleportEvent e) {
		Player player = e.getPlayer();
		
		if(PlayerTeleportCoords.playerTeleportCoords.containsKey(player.getUniqueId())) {
			PlayerTeleportCoords.playerTeleportCoords.remove(player.getUniqueId());
		}
		
		PlayerTeleportCoords.playerTeleportCoords.put(player.getUniqueId(), e.getFrom());
	}
}
