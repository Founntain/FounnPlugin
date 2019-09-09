package de.founntain.FounnPlugin.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CommandStartKit implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		System.out.println("StartKit Command");
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			PlayerInventory inv = player.getInventory();
			
			if(inv.firstEmpty() == 0) {
				player.sendMessage("§eIts dangerous to go alone! Take this.");
				
				ItemStack[] items = new ItemStack[5];
				
				items[0] = new ItemStack(Material.STONE_SWORD);
				items[1] = new ItemStack(Material.STONE_PICKAXE);
				items[2] = new ItemStack(Material.STONE_AXE);
				items[3] = new ItemStack(Material.STONE_SHOVEL);
				items[4] = new ItemStack(Material.APPLE, 16);
				
				inv.addItem(items);
			}else {
				player.sendMessage("§cYou don't need that!");
			}
			
			return true;
		}
		
		return false;
	}
}
