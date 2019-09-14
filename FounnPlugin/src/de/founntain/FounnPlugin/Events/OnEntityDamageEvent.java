package de.founntain.FounnPlugin.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.md_5.bungee.api.ChatColor;

public class OnEntityDamageEvent implements Listener{
	@EventHandler
	public void onEnityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			
			if(player.getHealth() < 5) {
				player.sendMessage("[" + ChatColor.RED + "!" + ChatColor.WHITE +"] Du bist kurz vorm abrippen. Heil dich!");
			}
		}
	}
}