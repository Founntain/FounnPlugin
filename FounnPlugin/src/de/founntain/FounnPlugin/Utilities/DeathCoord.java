package de.founntain.FounnPlugin.Utilities;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DeathCoord {
	public static HashMap<UUID, DeathCoord> deathCoords;
	
	private Location location;
	private UUID playerID;
	
	public DeathCoord(Location location, UUID playerID) {
		this.location = location;
		this.playerID = playerID;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public UUID getPlayerID() {
		return this.playerID;
	}
	
	public static DeathCoord getDeathCoordFromPlayer(Player player) {
		return deathCoords.get(player.getUniqueId());
	}
	
	public boolean equals(Location loc) {
		return this.location == loc;		
	}
}