package de.founntain.FounnPlugin;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DeathCoord {
	public static HashMap<UUID, DeathCoord> DeathCoords;
	
	private Location Location;
	private UUID PlayerID;
	
	public DeathCoord(Location location, UUID PlayerID) {
		this.Location = location;
		this.PlayerID = PlayerID;
	}
	
	public Location getLocation() {
		return this.Location;
	}
	
	public UUID getPlayerID() {
		return this.PlayerID;
	}
	
	public static DeathCoord getDeathCoordFromPlayer(Player player) {
		return DeathCoords.get(player.getUniqueId());
	}
	
	public boolean equals(Location loc) {
		return this.Location == loc;		
	}
}
