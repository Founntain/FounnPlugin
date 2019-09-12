package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.FounnPlugin;
import de.founntain.FounnPlugin.Utilities;
import de.founntain.FounnPlugin.Guis.AdminGui;
import de.founntain.FounnPlugin.Guis.MenuGui;
import de.founntain.FounnPlugin.Guis.TeleportGui;
import net.md_5.bungee.api.ChatColor;

public class OnInventoryClickEvent implements Listener{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {		
		Player player = (Player) e.getWhoClicked();
		TeleportGui teleportGui = null;
		
		if(player == null)
			return;
		
		if(e.getView().getTitle().contains("Servermenu")) {
			
			if(e.getCurrentItem() == null) {
				return;
			}
			
			switch(e.getCurrentItem().getType()) {
				case ENCHANTED_GOLDEN_APPLE:
					if(!player.getUniqueId().equals(FounnPlugin.FounntainUUID)) {
						player.closeInventory();
						player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + "Du hast nicht genug Berechtigungen!");
						e.setCancelled(true);
						return;
					}
					
					AdminGui adminGui = new AdminGui(player);
					adminGui.openAdminGui();
					break;
				case COMPASS:
					teleportGui = new TeleportGui(player, true);
					teleportGui.openTeleportGui();
					break;
				case LAVA_BUCKET:
					player.openInventory(Bukkit.createInventory(player, 54, ChatColor.RED + "Müllverbrennungsanlage"));
					break;
				default:
					break;
			}
			
			e.setCancelled(true);
			
			return;
		} 
		
		if(e.getView().getTitle().startsWith(ChatColor.DARK_PURPLE+ "Adminwerkzeuge")) {
			if(e.getCurrentItem() == null) {
				return;
			}
			
			switch(e.getCurrentItem().getType()) {
				case CLOCK:
					teleportGui = new TeleportGui(player, false);
					teleportGui.openTeleportGui();
					break;
				default:
					MenuGui menuGui = new MenuGui(player);
					menuGui.openMenuGui();
					break;
			}
			
			e.setCancelled(true);
			
			return;
		}
		
		if(e.getView().getTitle().startsWith(ChatColor.BLUE+ "Teleport")) {
			if(e.getCurrentItem() == null)
				return;
			
			ItemStack item = e.getCurrentItem();
			
			Player clickedPlayer = Bukkit.getPlayerExact(item.getItemMeta().getDisplayName().replace(ChatColor.YELLOW + "", ""));
			
			if(e.getView().getTitle().endsWith("to specific player")) {
				player.teleport(clickedPlayer.getLocation());
			}else if(e.getView().getTitle().endsWith("specific player to you")) {
				clickedPlayer.teleport(player.getLocation());
			}
			
			e.setCancelled(true);
			
			player.closeInventory();
			
			return;
		}
	}
}
