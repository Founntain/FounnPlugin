package de.founntain.FounnPlugin;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import de.founntain.FounnPlugin.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class PlayerStat {
	private UUID playerId;
	private HashMap<String, Integer> entityKills;
	private HashMap<Material, Integer> killedWithItems;
	
	public PlayerStat(UUID playerId) {
		this.playerId = playerId;
		this.entityKills = new HashMap<String, Integer>();
		this.killedWithItems = new HashMap<Material, Integer>();
	}
	
	public PlayerStat(UUID playerId, HashMap<String, Integer> entityKills, HashMap<Material, Integer> killedWithItems) {
		this.playerId = playerId;
		
		if(entityKills.size() == 0)
			this.entityKills = new HashMap<String, Integer>();
		else
			this.entityKills = entityKills;
		
		if(killedWithItems.size() == 0)
			this.killedWithItems = new HashMap<Material, Integer>();
		else
			this.killedWithItems = killedWithItems;
	}
	
	public UUID getPlayerId() {
		return this.playerId;
	}
	
	public HashMap<String, Integer> getEntityKillsHashMap(){
		return this.entityKills;
	}
	
	public HashMap<Material, Integer> getKilledWithItemsHashMap(){
		return this.killedWithItems;
	}
	
	public void updateEntityKillStats(LivingEntity entity, int amount) {
		String name = "";
		
		try {
			name = new String(entity.getName().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		int newValue = 0;
		
		if(this.entityKills.containsKey(name))
			newValue = this.entityKills.get(name) + amount;
		else
			newValue += amount;
		
		this.entityKills.put(entity.getName(), newValue);
		
		this.broadcastMilestone(name, newValue);
	}
	
	public void updateEntityKillMaterialStats(Material material, int amount) {
		int newValue = 0;
		
		if(this.killedWithItems.containsKey(material))
			newValue = this.killedWithItems.get(material) + amount;
		else
			newValue += amount;
		
		this.killedWithItems.put(material, newValue);
		
		this.broadcastMilestone(material, newValue);
	}
	
	private void broadcastMilestone(String name, int amount) {
		if(amount % 10 != 0) return;

		String prefix = Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Milestone");
		Player player = Bukkit.getServer().getPlayer(this.playerId);
		
		Bukkit.broadcastMessage(prefix + ChatColor.GOLD + player.getDisplayName() + " hat " + amount + " " + name + "s getötet!");
	
		Utilities.spawnRandomFirework(player.getLocation());
	}
	
	private void broadcastMilestone(Material material, int amount) {
		if(amount % 10 != 0) return;

		String prefix = Utilities.getCustomPrefix(ChatColor.DARK_PURPLE, "Milestone");
		Player player = Bukkit.getServer().getPlayer(this.playerId);
		
		String itemName = material.toString().replace('_', ' ');
		
		Bukkit.broadcastMessage(prefix + ChatColor.GOLD + player.getDisplayName() + " hat " + amount + " kills mit " + itemName + " gemacht!");
	
		Utilities.spawnRandomFirework(player.getLocation());
	}
}
