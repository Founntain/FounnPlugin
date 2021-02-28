package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class OnPlayerInteractEvent implements Listener{
	private Plugin plugin;
	
	public OnPlayerInteractEvent(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		ItemStack item = e.getItem();
		
		if(item == null) return;
		
		if(e.getItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "TNT Pickaxe")) {
			this.useTntPickaxe(e, item);
			return;
		}
	}
	
	private void useTntPickaxe(PlayerInteractEvent e, ItemStack item) {
		if(e.getAction() != Action.LEFT_CLICK_BLOCK) return;
		
		Player player = e.getPlayer();
		Block block = e.getClickedBlock();
		
		player.sendMessage("[" + ChatColor.BLUE + "W" + ChatColor.WHITE + "] " + ChatColor.GOLD + "Explosion in 5 Sekunden");
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			@Override
			public void run() {
				Location blockLocation = block.getLocation();
				
				blockLocation.getWorld().createExplosion(
						blockLocation.getX(),
						blockLocation.getY(),
						blockLocation.getZ(),
						5F, false, true);
				
				Damageable dmgMeta = (Damageable) item.getItemMeta();
				
				dmgMeta.setDamage((dmgMeta.getDamage() + 1));
				
				item.setItemMeta((ItemMeta) dmgMeta);
			}
		}, 100);
	}
}
