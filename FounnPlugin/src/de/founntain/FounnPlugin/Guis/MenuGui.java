package de.founntain.FounnPlugin.Guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.Utilities;

public class MenuGui {
	private Player Player;
	private Inventory Gui;
	
	public MenuGui(Player player) {
		this.Player = player;
		this.Gui = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "Servermenu");
	}
	
	public void openMenuGui() {
		
		ItemStack adminMenu = Utilities.createMenuItem(Material.ENCHANTED_GOLDEN_APPLE, ChatColor.DARK_PURPLE + "Adminwerkzeuge", new String[] {
				ChatColor.GRAY + "Tools für" + ChatColor.DARK_PURPLE + " Founntain ",
				ChatColor.GRAY +"um den Server zu verwalten!"
		});
		
		ItemStack tpToPlayer = Utilities.createMenuItem(Material.COMPASS, ChatColor.YELLOW + "Zu Spieler porten", new String[] {
				ChatColor.GRAY + "Teleportiere dich zu einem",
				ChatColor.GRAY + "anderem " + ChatColor.YELLOW + "Spieler" + ChatColor.GRAY + "!"
		});
		
		ItemStack trash = Utilities.createMenuItem(Material.LAVA_BUCKET, ChatColor.RED + "Müllverbrennungsanlage", new String[] {
			ChatColor.GRAY + "Werf dort alle Items rein,",
			ChatColor.GRAY + "die du nicht mehr brauchst, ",
			ChatColor.GRAY + "bitte bedenke, dass deine Items " + ChatColor.BOLD + ChatColor.UNDERLINE + "nicht",
			ChatColor.RESET.toString() + ChatColor.GRAY + "wiederhergestellt werden können"
		});
		
		ItemStack[] menuItems = {
				adminMenu,
				tpToPlayer,
				trash
		};
		
		this.Gui.setContents(menuItems);
		
		this.Player.openInventory(this.Gui);
	}
}
