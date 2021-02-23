package de.founntain.FounnPlugin.Guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.Utilities;
import net.md_5.bungee.api.ChatColor;

public class AdminGui {
	private Player player;
	private Inventory gui;
	
	public AdminGui(Player player) {
		this.player = player;
		this.gui = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "Adminwerkzeuge");
	}
	
	public void openAdminGui() {
		
		ItemStack tpPlayerToSelf = Utilities.createMenuItem(Material.CLOCK, ChatColor.YELLOW + "Teleport player to you", new String[]{
			ChatColor.GRAY + "Teleportiere einen anderen " + ChatColor.YELLOW + "Spieler",
			ChatColor.GRAY + " zu dir!"
		});
		
		ItemStack cancel = Utilities.createMenuItem(Material.RED_STAINED_GLASS_PANE, ChatColor.RED + "Zurück zum Hauptmenu");
		
		ItemStack workbench = Utilities.createMenuItem(Material.CRAFTING_TABLE, "Werkbank öffnen");
		
		ItemStack filler = Utilities.createMenuItem(Material.GRAY_STAINED_GLASS_PANE, " ");
		
		ItemStack[] menuItems = {
			tpPlayerToSelf,
			workbench,
			filler,
			filler,
			filler,
			filler,
			filler,
			filler,
			cancel
		};
		
		this.gui.setContents(menuItems);
		
		this.player.openInventory(this.gui);
	}
}
