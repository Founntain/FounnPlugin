package de.founntain.FounnPlugin.Guis;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import de.founntain.FounnPlugin.Utilities.Utilities;

public class TeleportGui {
	private Player player;
	private Inventory gui;
	private boolean toPlayer;
	
	public TeleportGui(Player player, boolean toPlayer) {
		this.player = player;
		this.gui = Bukkit.createInventory(player, 36, ChatColor.BLUE+ "Teleport " + (toPlayer
				? "to specific player"
				: "specific player to you"));
		this.toPlayer = toPlayer;
	}
	
	public void openTeleportGui() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		
		for(Player onlinePlayer : Bukkit.getOnlinePlayers()) {
			if(onlinePlayer.getUniqueId().equals(this.player.getUniqueId()))
				continue;
			
			ItemStack head = new ItemStack(Material.PLAYER_HEAD);
			
			SkullMeta headMeta = (SkullMeta) head.getItemMeta();
			headMeta.setOwningPlayer(onlinePlayer);
			headMeta.setDisplayName(ChatColor.YELLOW + onlinePlayer.getDisplayName());
			
			ArrayList<String> headLore = new ArrayList<String>();
			
			Location loc = onlinePlayer.getLocation();
			
			headLore.add(ChatColor.GRAY + "Current World: " + Utilities.getFormatedEnvironmentName(loc.getWorld().getEnvironment()));
			
			if(this.toPlayer) {
				headLore.add(ChatColor.GRAY + "Last known location:");
				headLore.add("X: " +(int)loc.getX());
				headLore.add("Y: " +(int)loc.getY());
				headLore.add("Z: " +(int)loc.getZ());
				
				headMeta.setLore(headLore);
			}
			
			head.setItemMeta(headMeta);
			
			items.add(head);
		}
		
		ItemStack[] itemsToAdd = new ItemStack[items.size()];
		itemsToAdd = items.toArray(itemsToAdd);
		
		this.gui.setContents(itemsToAdd);
		
		this.player.openInventory(this.gui);
	}
}
