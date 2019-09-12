package de.founntain.FounnPlugin.Guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.Utilities;
import net.md_5.bungee.api.ChatColor;

public class AdminGui {
	private Player Player;
	private Inventory Gui;
	
	public AdminGui(Player player) {
		this.Player = player;
		this.Gui = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "Adminwerkzeuge");
	}
	
	public void openAdminGui() {
		
		ItemStack tpPlayerToSelf = Utilities.createMenuItem(Material.CLOCK, ChatColor.YELLOW + "Teleport player to you", new String[]{
			ChatColor.GRAY + "Teleportiere einen anderen " + ChatColor.YELLOW + "Spieler",
			ChatColor.GRAY + " zu dir!"
		});
		
		ItemStack cancel = Utilities.createMenuItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Zurück zum Hauptmenu");
		
		ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		
		ItemStack[] menuItems = {
			tpPlayerToSelf,
			filler,
			filler,
			filler,
			filler,
			filler,
			filler,
			filler,
			cancel
		};
		
		this.Gui.setContents(menuItems);
		
		this.Player.openInventory(this.Gui);
	}
}
