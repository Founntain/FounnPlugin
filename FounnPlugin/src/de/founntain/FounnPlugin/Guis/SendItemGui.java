package de.founntain.FounnPlugin.Guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;

public class SendItemGui {
	private Player Player;
	private Inventory Gui;
	
	public SendItemGui(Player player, Player receiver) {
		this.Player = player;
		this.Gui = Bukkit.createInventory(player, 27, ChatColor.BLUE + "Receiver: " + receiver.getDisplayName());
	}
	
	public void openSendItemToPlyerGui() {
		this.Player.openInventory(this.Gui);
	}
}
