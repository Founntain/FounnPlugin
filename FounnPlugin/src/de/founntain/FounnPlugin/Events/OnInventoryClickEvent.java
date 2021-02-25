package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.founntain.FounnPlugin.FounnPlugin;
import de.founntain.FounnPlugin.Guis.AdminGui;
import de.founntain.FounnPlugin.Guis.EnchantmentGui;
import de.founntain.FounnPlugin.Guis.MenuGui;
import de.founntain.FounnPlugin.Guis.TeleportGui;
import de.founntain.FounnPlugin.Utilities.DeathItems;
import de.founntain.FounnPlugin.Utilities.Utilities;
import net.md_5.bungee.api.ChatColor;

public class OnInventoryClickEvent implements Listener{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {		
		Player player = (Player) e.getWhoClicked();

		if(player == null)
			return;
		
		if(e.getView().getTitle().contains("Servermenu")) {
			this.menuInventory(e, player);
			return;
		} 
		
		if(e.getView().getTitle().startsWith(ChatColor.DARK_PURPLE+ "Adminwerkzeuge")) {
			this.adminToolsInventory(e, player);
			return;
		}
		
		if(e.getView().getTitle().startsWith(ChatColor.BLUE + "Teleport")) {			
			this.teleportToPlayerInventory(e, player);
			
			return;
		}
		
		if(e.getView().getTitle().equals(player.getDisplayName() + " DeathItems")) {		
			this.deathItemsInventory(e, player);
			
			return;
		}
	}
	
	private void menuInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem() == null) 
			return;
		
		TeleportGui teleportGui = null;
		
		switch(e.getCurrentItem().getType()) {
			case ENCHANTED_GOLDEN_APPLE:
				if(!player.getUniqueId().equals(FounnPlugin.founntainUUID)) {
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
			case ENCHANTED_BOOK:
				EnchantmentGui enchantmentGui = new EnchantmentGui(player);
				enchantmentGui.openGui();
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
	
	private void adminToolsInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem() == null)
			return;
		
		TeleportGui teleportGui = null;
		
		switch(e.getCurrentItem().getType()) {
			case CLOCK:
				teleportGui = new TeleportGui(player, false);
				teleportGui.openTeleportGui();
				break;
			case CRAFTING_TABLE:
				player.openWorkbench(null, true);
				break;
			case GRAY_STAINED_GLASS_PANE:
				break;
			default:
				MenuGui menuGui = new MenuGui(player);
				menuGui.openMenuGui();
				break;
		}
		
		e.setCancelled(true);
	}
	
	private void teleportToPlayerInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem() == null)
			return;
		
		if(e.getCurrentItem().getType() != Material.PLAYER_HEAD) {
			e.setCancelled(true);
			return;
		}

		ItemStack item = e.getCurrentItem();
		
		Player clickedPlayer = Bukkit.getPlayerExact(item.getItemMeta().getDisplayName().replace(ChatColor.YELLOW + "", ""));
		
		if(e.getView().getTitle().endsWith("to specific player")) 
			player.teleport(clickedPlayer.getLocation());
		else if(e.getView().getTitle().endsWith("specific player to you")) 
			clickedPlayer.teleport(player.getLocation());
		
		
		e.setCancelled(true);
		
		player.closeInventory();
	}
	
	private void deathItemsInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem().getType() == Material.CHEST) {
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Alles nehmen")) {
				for(ItemStack item : e.getInventory().getContents()) {
					if(item == null)
						continue;
					
					if(item.getType() == Material.CHEST && item.getItemMeta().getDisplayName().equals(ChatColor.DARK_PURPLE + "Alles nehmen")) 
						continue;		
					
					player.getInventory().addItem(item);
				}
				
				player.closeInventory();
				
				DeathItems.items.remove(player.getUniqueId());
			}
		}
	}
}
