package de.founntain.FounnPlugin.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ChunkScanCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			long time = System.nanoTime();
			
			scanChunk((Player) sender);
			
			time = System.nanoTime() - time;
			
			((Player) sender).sendMessage(ChatColor.YELLOW + " scanned chunk in " + (time / 1000000.0D) + "ms");
			
			return true;
		}

		return false;
	}

	private void scanChunk(final Player player) {
		Chunk chunk = player.getLocation().getChunk();
		HashMap<Material, Integer> blocks = new HashMap<Material, Integer>();
		
		ArrayList<Material> materialsToIgnore = new ArrayList<Material>();
		
		materialsToIgnore.add(Material.AIR);
		materialsToIgnore.add(Material.DIRT);
		materialsToIgnore.add(Material.STONE);
		materialsToIgnore.add(Material.ANDESITE);
		materialsToIgnore.add(Material.DIORITE);
		materialsToIgnore.add(Material.GRANITE);
		
		int minX = 0;
		int minZ = 0;
		int maxX = minX | 15;
		int maxY = chunk.getWorld().getMaxHeight() - 1;
		int maxZ = minZ | 15;
		
		for(int x = minX; x <= maxX; x++) {
			for(int y = 0; y <= maxY; y++) {
				for(int z = minZ; z <= maxZ; z++) {					
					Block block = chunk.getBlock(x, y, z); 
					
					if(materialsToIgnore.contains(block.getType())) 
						 continue;
									
					int amount = 0;
						
					if(blocks.containsKey(block.getType()))
						amount += (blocks.get(block.getType()) + 1);
					
					blocks.put(block.getType(), amount);					
				}
			}
		}
		
		this.showResult(player, blocks);
	}
	
	private void showResult(final Player player, final HashMap<Material, Integer> blocks) {
		Inventory inv = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Chunkscan result");
		
		for(Entry<Material, Integer> entry : blocks.entrySet()) {
			ItemStack item = new ItemStack(entry.getKey());
			ItemMeta meta = item.getItemMeta();
			
			meta.setDisplayName(ChatColor.YELLOW + "" + entry.getValue() + " " + entry.getKey().name());

			item.setItemMeta(meta);
			
			inv.addItem(item);
		}
		
		player.openInventory(inv);
	}
}
