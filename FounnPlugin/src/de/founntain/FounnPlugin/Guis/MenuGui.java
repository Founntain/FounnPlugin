package de.founntain.FounnPlugin.Guis;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.Utilities;

public class MenuGui {
	private Player player;
	private Inventory gui;
	
	public MenuGui(Player player) {
		this.player = player;
		this.gui = Bukkit.createInventory(player, 9, ChatColor.DARK_PURPLE + "Servermenu");
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
		ItemStack enchantItem = Utilities.createMenuItem(Material.ENCHANTED_BOOK, ChatColor.YELLOW + "Item verzaubern", new String[] {
			ChatColor.GRAY + "Verzaubere ein Item mit einem verzauberten Buch!"	
		});
		
		ItemStack trash = Utilities.createMenuItem(Material.LAVA_BUCKET, ChatColor.RED + "Müllverbrennungsanlage", new String[] {
			ChatColor.GRAY + "Werf dort alle Items rein,",
			ChatColor.GRAY + "die du nicht mehr brauchst, ",
			ChatColor.GRAY + "bitte bedenke, dass deine Items " + ChatColor.BOLD + ChatColor.UNDERLINE + "nicht",
			ChatColor.RESET.toString() + ChatColor.GRAY + "wiederhergestellt werden können"
		});
		
		ItemStack filler = Utilities.createMenuItem(Material.GRAY_STAINED_GLASS_PANE, " ");
		
		ItemStack[] menuItems = {
				adminMenu,
				tpToPlayer,
				enchantItem,
				trash,
				filler,
				filler,
				filler,
				filler,
				filler
		};
		
		this.gui.setContents(menuItems);
		
		this.player.openInventory(this.gui);
	}
}
