package de.founntain.founnplugin.custom_mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class FreedomfighterMob {
	
	private Plugin plugin;
	
	public FreedomfighterMob(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public void spawnFreedomFighter(Location location, Player player) {
		Zombie zombie = (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
		
		zombie.setCustomName(ChatColor.DARK_PURPLE + "Sprüh Sprüh Knallbummman");
		zombie.setCustomNameVisible(true);
		zombie.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 3));
		zombie.setMetadata("boomSpawnerZombie", new FixedMetadataValue(this.plugin, "boomSpawnerZombie"));
		zombie.setTarget(player);
		
		zombie.getEquipment().setHelmet(new ItemStack(Material.TNT));
		
		startMinionSpawn(this.plugin, zombie, player);
	}
	
	private void startMinionSpawn(Plugin plugin, Zombie zombie, Player player) {
		new BukkitRunnable() {		
			@Override
			public void run() {
				if(zombie.isDead()) {
					
					for(Zombie z : Bukkit.getWorld("world").getEntitiesByClass(Zombie.class)) {
						if(z.hasMetadata("boomZombie")) {
							Location loc = z.getLocation();
							
							z.setHealth(0);
							
							loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 1, false, false);
						}
					}
					
					cancel();
					return;
				}
				
				Zombie zombieBaby = (Zombie) zombie.getLocation().getWorld().spawnEntity(zombie.getLocation(), EntityType.ZOMBIE);
				
				zombieBaby.setMetadata("boomZombie", new FixedMetadataValue(plugin, "boomZombie"));	
				
				zombieBaby.setTarget(player);
				zombieBaby.setHealth(0.1);
				
				EntityEquipment equipment = zombieBaby.getEquipment();
				equipment.setHelmet(new ItemStack(Material.TNT));
				equipment.setItemInMainHand(new ItemStack(Material.TNT));
			}
		}.runTaskTimer(this.plugin, 20, 50);
	}
}
