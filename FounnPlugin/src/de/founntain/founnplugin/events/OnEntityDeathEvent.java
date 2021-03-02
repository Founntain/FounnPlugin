package de.founntain.founnplugin.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import de.founntain.founnplugin.FounnPlugin;
import de.founntain.founnplugin.classes.PlayerStat;

public class OnEntityDeathEvent implements Listener{	
	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent e) {
		this.savePlayerStats(e);
	}

	private void savePlayerStats(EntityDeathEvent e) {
		
		if(e.getEntity() instanceof Zombie) {
			Zombie zombie = (Zombie) e.getEntity();
			
			if(zombie.hasMetadata("boomSpawnerZombie")) 
				return;
		}
		
		LivingEntity livingEntity = e.getEntity();
		Player player = livingEntity.getKiller();
		
		if(player == null) return;
		
		PlayerStat playerStat = FounnPlugin.playerStatsManager.getStatsOfPlayer(player.getUniqueId());
		
		playerStat.updateEntityKillStats(livingEntity, 1);
		playerStat.updateEntityKillMaterialStats(player.getInventory().getItemInMainHand().getType(), 1);
		
		FounnPlugin.playerStatsManager.saveStatsOfPlayer(playerStat);
	}
}
