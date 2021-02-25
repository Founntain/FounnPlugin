package de.founntain.FounnPlugin.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!player.isOp())
				 return false;
			
			ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
			ItemMeta itemMeta = item.getItemMeta();
			
			itemMeta.addEnchant(Enchantment.DIG_SPEED, 10, true);
			
			item.setItemMeta(itemMeta);
			
			player.getInventory().addItem(item);
			
			return true;
		}
			
		return false;
	}
}
