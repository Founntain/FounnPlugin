package de.founntain.FounnPlugin.Guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import net.md_5.bungee.api.ChatColor;

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
