package de.founntain.FounnPlugin.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnEntityDamageByEntityEvent implements Listener{
	@EventHandler
	public void onEntityDamageByEnity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Zombie && e.getEntity() instanceof Player) {
			Zombie zombie = (Zombie) e.getDamager();
			Location loc = zombie.getLocation();
			
			if(zombie.hasMetadata("boomZombie")) 
				zombie.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 1, false, false);	
		}
	}
}
