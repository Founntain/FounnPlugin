package de.founntain.founnplugin.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;

public class SendItemGui {
	private Player player;
	private Inventory gui;
	
	public SendItemGui(Player player, Player receiver) {
		this.player = player;
		this.gui = Bukkit.createInventory(player, 27, ChatColor.BLUE + "Receiver: " + receiver.getDisplayName());
	}
	
	public void openSendItemToPlyerGui() {
		this.player.openInventory(this.gui);
	}
}
