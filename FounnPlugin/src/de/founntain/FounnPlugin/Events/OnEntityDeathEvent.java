package de.founntain.FounnPlugin.Events;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import de.founntain.FounnPlugin.Utilities;
import de.founntain.FounnPlugin.CustomMobs.FreedomfighterMob;

public class OnEntityDeathEvent implements Listener{
	private Plugin plugin;
	
	public OnEntityDeathEvent(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent e) {
		
		if(e.getEntity() instanceof Zombie) {
			
			Zombie zombie = (Zombie) e.getEntity();
			
			if(zombie.hasMetadata("boomZombie")) {
				Utilities.spawnRandomFirework(zombie.getLocation());
				return;
			}
			
			if(zombie.hasMetadata("boomSpawnerZombie")) {
				Utilities.spawnRandomFirework(zombie.getLocation());
				
				Random rdm = new Random();
				
				ItemStack[] dropItems = {
						new ItemStack(Material.ROTTEN_FLESH, rdm.nextInt(4) + 1),
						new ItemStack(Material.GUNPOWDER, rdm.nextInt(7)),
						new ItemStack(Material.TNT, rdm.nextInt(4) + 1)
				};
				
				ExperienceOrb xpOrb = zombie.getWorld().spawn(zombie.getLocation(), ExperienceOrb.class);
				
				xpOrb.setExperience(rdm.nextInt(10) + 1);
				
				for(ItemStack item : dropItems)
					zombie.getWorld().dropItem(zombie.getLocation(), item);
				
				return;
			}
		}
		
		if(e.getEntity() instanceof Zombie && e.getEntity().getKiller() instanceof Player) {		
			
			Zombie zombie = (Zombie) e.getEntity();
			
			if(zombie.isBaby())
				return;
			
			Random rdm = new Random();
			
			int val = rdm.nextInt(101);
			
			if(val < 40) {
				Location loc = e.getEntity().getLocation();
				Player player = e.getEntity().getKiller();

				FreedomfighterMob freedomfighterMob = new FreedomfighterMob(this.plugin);
				freedomfighterMob.spawnFreedomFighter(loc, player);
			}
		}
	}

}
