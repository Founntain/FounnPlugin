package de.founntain.FounnPlugin.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

import de.founntain.FounnPlugin.Utilities.BedMap;

public class OnPlayerBedLeaveEvent implements Listener{
	@EventHandler
	public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
		BedMap.removeIfExists(e.getPlayer().getUniqueId());
	}
}
