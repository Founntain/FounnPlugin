package de.founntain.FounnPlugin.Events.InventoryClick;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.founntain.FounnPlugin.FounnPlugin;
import de.founntain.FounnPlugin.Guis.AdminGui;
import de.founntain.FounnPlugin.Guis.EnchantmentGui;
import de.founntain.FounnPlugin.Guis.PlayerStatsGui;
import de.founntain.FounnPlugin.Guis.TeleportGui;
import de.founntain.FounnPlugin.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class OnServerMenuInventoryClick implements Listener{
	@EventHandler
	public void onServerMenuClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if(player == null) return;
		
		if(!e.getView().getTitle().contains("Servermenu")) return;
		
		this.handleInventory(e, player);
		e.setCancelled(true);
	}
	
	private void handleInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem() == null) 
			return;
		
		TeleportGui teleportGui = null;
		
		switch(e.getCurrentItem().getType()) {
			case ENCHANTED_GOLDEN_APPLE:
				if(!player.getUniqueId().equals(FounnPlugin.founntainUUID)) {
					player.closeInventory();
					player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + "Du hast nicht genug Berechtigungen!");
					return;
				}
				
				AdminGui adminGui = new AdminGui(player);
				adminGui.openAdminGui();
				return;
			case NETHER_STAR:
				PlayerStatsGui playerStatsGui = new PlayerStatsGui(player);
				playerStatsGui.openGui();
				return;
			case COMPASS:
				teleportGui = new TeleportGui(player, true);
				teleportGui.openTeleportGui();
				return;
			case ENCHANTED_BOOK:
				EnchantmentGui enchantmentGui = new EnchantmentGui(player);
				enchantmentGui.openGui();
				break;
			case LAVA_BUCKET:
				player.openInventory(Bukkit.createInventory(player, 54, ChatColor.RED + "Müllverbrennungsanlage"));
				return;
			default:
				return;
		}
	}
}
