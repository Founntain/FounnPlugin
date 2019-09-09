package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.founntain.FounnPlugin.DeathCoord;

public class OnPlayerDeathEvent implements Listener{
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		e.setDeathMessage(null);
		
		if(e.getEntity() instanceof Player) {					
			Player player = e.getEntity();
			
			Location loc = player.getLocation();
			
			String deathMessage;
			
			DeathCoord deathCoord = new DeathCoord(loc, player.getUniqueId());
			DeathCoord deathCoordToDelete = null;

			for (DeathCoord dc : DeathCoord.DeathCoords) {			
				if (dc.getPlayerID().equals(player.getUniqueId())) {
					deathCoordToDelete = dc;
				}
			} 

			DeathCoord.DeathCoords.remove(deathCoordToDelete);
			
			DeathCoord.DeathCoords.add(deathCoord);
			
			deathMessage = ChatColor.RED + "Du bist gestorben." + " Schreib" + ChatColor.GREEN + " /back "
					+ ChatColor.RED + "um dich zu deinem Todespunkt zu teleportieren";
			
			player.sendMessage(deathMessage);

			
			Bukkit.broadcastMessage("[" + ChatColor.GRAY + "†" + ChatColor.WHITE + "] " + ChatColor.GRAY + player.getDisplayName() +
					ChatColor.RED + " -" + e.getDroppedExp() + "XP" );
		}
	}
}
