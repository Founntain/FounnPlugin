package dev.founntain.founnplugin.guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
