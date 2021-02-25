package de.founntain.FounnPlugin.CustomMobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import de.founntain.FounnPlugin.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class FireworkCreeperMob {
	private Plugin plugin;
	
	public FireworkCreeperMob(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public void spawnMob(Location location, Player player) {
		Creeper creeper = (Creeper) location.getWorld().spawnEntity(location, EntityType.CREEPER);
		
		creeper.setCustomName(ChatColor.DARK_PURPLE + "Silversterparty");
		creeper.setCustomNameVisible(true);
		creeper.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000, 3));
		creeper.setMetadata("silversterparty", new FixedMetadataValue(this.plugin, "silversterparty"));
		creeper.setTarget(player);
		creeper.setMaxFuseTicks(1000000000);
		
		creeper.getEquipment().setHelmet(new ItemStack(Material.PURPLE_STAINED_GLASS));
		
		this.startFireworkSpawning(this.plugin, creeper, player);
	}

	
	private void startFireworkSpawning(Plugin plugin, Creeper creeper, Player player) {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				if(creeper.isDead()){
					
					cancel();
					return;
				}
				
				Utilities.spawnRandomFirework(creeper.getLocation());
			}
		}.runTaskTimer(this.plugin, 2, 4);
	}
}
