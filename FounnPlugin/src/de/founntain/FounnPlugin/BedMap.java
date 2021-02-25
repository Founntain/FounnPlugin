package de.founntain.FounnPlugin;

import java.util.HashMap;
import java.util.UUID;

public class BedMap {
	public static HashMap<UUID, Boolean> usersInBeds;
	
	public static void removeIfExists(UUID id) {
		if(usersInBeds.containsKey(id))
			usersInBeds.remove(id);
	}
}
