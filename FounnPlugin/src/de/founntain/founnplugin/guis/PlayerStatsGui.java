package de.founntain.founnplugin.guis;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.founntain.founnplugin.FounnPlugin;
import de.founntain.founnplugin.classes.PlayerStat;
import de.founntain.founnplugin.classes.Utilities;

public class PlayerStatsGui {
	private Player player;
	private PlayerStat playerStat;
	private Inventory gui;
	
	public PlayerStatsGui(Player player) {
		this.player = player;
		this.playerStat = FounnPlugin.playerStatsManager.getStatsOfPlayer(player.getUniqueId());
		this.gui = Bukkit.createInventory(player, 36, ChatColor.DARK_PURPLE + player.getDisplayName() + "s Statistik");
	}
	
	public void openGui() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		
		for(Map.Entry<String, Integer> entry : this.playerStat.getEntityKillsHashMap().entrySet()) {
			String name = entry.getKey();
			int amount = entry.getValue();
			
			ItemStack item = Utilities.createMenuItem(Material.ENDER_PEARL, ChatColor.DARK_PURPLE + (amount + "") + " " + ChatColor.GOLD + name + " kills");
			items.add(item);
		}
		
		ItemStack[] itemsToAdd = new ItemStack[items.size()];
		itemsToAdd = items.toArray(itemsToAdd);
		
		this.gui.setContents(itemsToAdd);
		
		this.player.openInventory(this.gui);
	}
}
