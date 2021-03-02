package de.founntain.founnplugin.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.founntain.founnplugin.FounnPlugin;
import de.founntain.founnplugin.classes.DeathItems;
import de.founntain.founnplugin.classes.Utilities;
import de.founntain.founnplugin.classes.WorldGenerator;
import de.founntain.founnplugin.guis.AdminGui;
import de.founntain.founnplugin.guis.EnchantmentGui;
import de.founntain.founnplugin.guis.MenuGui;
import de.founntain.founnplugin.guis.PlayerStatsGui;
import de.founntain.founnplugin.guis.TeleportGui;

public class OnCustomInventoryClickEvent implements Listener{
	@EventHandler
	public void onServerMenuInventoryClick(InventoryClickEvent e) {
		if(!e.getView().getTitle().contains("Servermenu")) return;
		
		Player player = (Player) e.getWhoClicked();
		
		this.serverMenuInventory(e, player);
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onAdminToolsInventoryClick(InventoryClickEvent e) {
		if(!e.getView().getTitle().startsWith(ChatColor.DARK_PURPLE+ "Adminwerkzeuge")) return;
		
		Player player = (Player) e.getWhoClicked();
		
		this.adminToolsInventory(e, player);
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDeathItemsInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		
		if(!e.getView().getTitle().equals(player.getDisplayName() + " DeathItems")) return;
		
		this.deathItemsInventory(e, player);
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onTeleportPlayerInventoryClick(InventoryClickEvent e) {
		if(!e.getView().getTitle().startsWith(ChatColor.BLUE + "Teleport")) return;
		
		Player player = (Player) e.getWhoClicked();
		
		this.teleportPlayerInventory(e, player);
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onTeleportPlayerToWorldInventoryClick(InventoryClickEvent e) {
		if(!e.getView().getTitle().equals(ChatColor.DARK_GREEN + "World list")) return;
		
		Player player = (Player) e.getWhoClicked();
		
		this.telportPlayerToWorldMenuInventory(e, player);
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onDeleteWorldInventoryClick(InventoryClickEvent e) {
		if(!e.getView().getTitle().equals(ChatColor.RED + "Delete world")) return;
		
		Player player = (Player) e.getWhoClicked();
		
		this.deleteWorldInventoryClick(e, player);
		e.setCancelled(true);
	}
	
	private void serverMenuInventory(final InventoryClickEvent e, final Player player) {
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
	
	private void adminToolsInventory(final InventoryClickEvent e, final Player player) {
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
	
	private void deathItemsInventory(final InventoryClickEvent e, final Player player) {
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
	
	private void teleportPlayerInventory(final InventoryClickEvent e, final Player player) {
		if(e.getCurrentItem() == null) return;
		if(e.getCurrentItem().getType() != Material.PLAYER_HEAD) return;
		
		ItemStack item = e.getCurrentItem();
		
		Player clickedPlayer = Bukkit.getPlayerExact(item.getItemMeta().getDisplayName().replace(ChatColor.YELLOW + "", ""));
		
		if(e.getView().getTitle().endsWith("to specific player")) 
			player.teleport(clickedPlayer.getLocation());
		else if(e.getView().getTitle().endsWith("specific player to you")) 
			clickedPlayer.teleport(player.getLocation());
		
		e.setCancelled(true);
		
		player.closeInventory();
	}
	
	private void telportPlayerToWorldMenuInventory(final InventoryClickEvent e, final Player player) {
		ItemStack itemClicked = e.getCurrentItem();
		
		if(itemClicked == null || itemClicked.getType() == Material.AIR) return;
		
		String worldname = itemClicked.getItemMeta().getDisplayName();
		
		for(World world : Bukkit.getServer().getWorlds()) {
			if(!(ChatColor.YELLOW + world.getName()).equals(worldname)) continue;
			
			player.teleport(world.getSpawnLocation());
			
			e.setCancelled(true);
			return;
		}
	}
	
	private void deleteWorldInventoryClick(final InventoryClickEvent e, final Player player) {
		ItemStack itemClicked = e.getCurrentItem();
		
		if(itemClicked == null || itemClicked.getType() == Material.AIR) return;
		
		String worldname = itemClicked.getItemMeta().getDisplayName().substring((ChatColor.YELLOW.toString().length()));
		
		for(World world : Bukkit.getServer().getWorlds()) {
			String wname = world.getName();
			
			if(!(wname).equals(worldname)) continue;
			
			List<Player> playersInWorld = world.getPlayers();
			
			for(Player p : playersInWorld) {
				World mainWorld = Bukkit.getWorld("world");
				
				p.teleport(mainWorld.getSpawnLocation());
				
				player.sendMessage(Utilities.getCustomPrefix(ChatColor.RED, "W") + ChatColor.RED + "Die Welt in der du bist wird gelöscht, du wurdest zum Spawn der Hauptwelt teleportiert!");
			}
			
			WorldGenerator wg = new WorldGenerator(worldname, player);
			wg.deleteWorld();
			
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.BLUE, "W") + "World " + worldname + " deleted!");
			Bukkit.getServer().getConsoleSender()
				.sendMessage(Utilities.getCustomPrefix(ChatColor.RED, "W") + "deleted world " + ChatColor.DARK_RED + worldname);
			
			player.closeInventory();
			return;
		}
	}
}
