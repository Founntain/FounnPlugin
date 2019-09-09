package de.founntain.FounnPlugin;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DeathCoord {
	public static ArrayList<DeathCoord> DeathCoords;
	
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
		for(DeathCoord d : DeathCoords) {
			if(!d.getPlayerID().equals(player.getUniqueId())) 
				continue;
				
			return d;
		}
		
		return null;
	}
	
	public boolean equals(Location loc) {
		return this.Location == loc;		
	}
}
