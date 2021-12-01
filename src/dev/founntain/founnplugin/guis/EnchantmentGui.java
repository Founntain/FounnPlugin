package dev.founntain.founnplugin.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnchantmentGui {
	private Player player;
	private Inventory gui;
	
	public EnchantmentGui(Player player) {
		this.player = player;
		this.gui = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "Verzaubere Item");
	}
	
	public void openGui() {	
		this.player.openInventory(this.gui);
	}
}
