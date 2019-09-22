package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.founntain.FounnPlugin.DeathCoord;
import de.founntain.FounnPlugin.DeathItems;

public class OnPlayerDeathEvent implements Listener{
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		
		e.setDeathMessage(null);
		
		if(e.getEntity() instanceof Player) {					
			Player player = e.getEntity();
			
			Location loc = player.getLocation();
			
			String deathMessage;
			
			DeathCoord deathCoord = new DeathCoord(loc, player.getUniqueId());
			DeathCoord.DeathCoords.remove(player.getUniqueId());
			
			DeathCoord.DeathCoords.put(player.getUniqueId(), deathCoord);
			
			deathMessage = ChatColor.RED + "Du bist gestorben." + " Schreib" + ChatColor.GREEN + " /back "
					+ ChatColor.RED + "um dich zu deinem Todespunkt zu teleportieren";
			
			player.sendMessage(deathMessage);

			Bukkit.broadcastMessage("[" + ChatColor.GRAY + "†" + ChatColor.WHITE + "] " + ChatColor.GRAY + player.getDisplayName() +
					ChatColor.RED + " -" + e.getDroppedExp() + "XP" );
			
			//Deathbox
			Inventory inventory = player.getInventory();
			for(ItemStack item : inventory.getContents()) {
				if(item == null)
					continue;
				if(item.getType() == Material.CHEST) {
					ItemMeta metaData = item.getItemMeta();
					
					if(metaData.getDisplayName().equals(ChatColor.DARK_PURPLE + "Deathbox")) {						
						DeathItems.Items.put(player.getUniqueId(), inventory.getContents());
						e.getDrops().removeAll(e.getDrops());
						
						player.sendMessage(ChatColor.DARK_PURPLE + "Deathbox" + ChatColor.WHITE + " >> " + ChatColor.YELLOW + "Du hattest eine Deathbox dabei, schreibe " + ChatColor.DARK_PURPLE+ "/deathbox" + ChatColor.YELLOW + " um an deine Items zu kommen!");
					}			
				}
			}
		}
	}
}
