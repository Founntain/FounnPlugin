package de.founntain.FounnPlugin.Events.InventoryClick;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.founntain.FounnPlugin.Guis.MenuGui;
import de.founntain.FounnPlugin.Guis.TeleportGui;
import net.md_5.bungee.api.ChatColor;

public class OnAdminToolsInventoryClick implements Listener{
	@EventHandler
	public void onServerMenuClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();

		if(player == null) return;
		
		if(!e.getView().getTitle().startsWith(ChatColor.DARK_PURPLE+ "Adminwerkzeuge")) return;
		
		this.handleInventory(e, player);
		e.setCancelled(true);
	}
	
	private void handleInventory(InventoryClickEvent e, Player player) {
		if(e.getCurrentItem() == null)
			return;
		
		TeleportGui teleportGui = null;
		
		switch(e.getCurrentItem().getType()) {
			case CLOCK:
				teleportGui = new TeleportGui(player, false);
				teleportGui.openTeleportGui();
				return;
			case CRAFTING_TABLE:
				player.openWorkbench(null, true);
				return;
			case GRAY_STAINED_GLASS_PANE:
				return;
			default:
				MenuGui menuGui = new MenuGui(player);
				menuGui.openMenuGui();
				return;
		}
	}
}
