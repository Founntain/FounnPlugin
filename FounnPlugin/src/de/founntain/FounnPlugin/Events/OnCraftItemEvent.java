package de.founntain.FounnPlugin.Events;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import de.founntain.FounnPlugin.Pair;

public class OnCraftItemEvent implements Listener{
	
	public OnCraftItemEvent() { }

	@EventHandler
	public void onCraftItemEvent(CraftItemEvent e) {		
		try {
			this.tryEnchantPlus(e);
		}catch(ClassCastException ex) {
			return;
		}	
	}
	
	private void tryEnchantPlus(CraftItemEvent e) throws ClassCastException{
		Recipe recipe = e.getRecipe();
		
		if(recipe == null)
			return;
		
		ItemStack[] contents =  e.getInventory().getContents();
		
		Pair<Enchantment, Integer> ench1 = null;
		Pair<Enchantment, Integer> ench2 = null;
		
		for(ItemStack stack : contents) {
			if(stack.getType() == Material.AIR)
				continue;

			EnchantmentStorageMeta enchMeta = (EnchantmentStorageMeta) stack.getItemMeta();
			
			int storedCount = enchMeta.getStoredEnchants().keySet().size();
			int count = enchMeta.getEnchants().keySet().size();
			
			if(storedCount == 0 && count == 0)
				continue;
			
			Map<Enchantment, Integer> enchs = null;
			
			if(storedCount > 0)
				enchs = enchMeta.getStoredEnchants();
			else if(count > 0)
				enchs = enchMeta.getEnchants();
			
			for(Map.Entry<Enchantment, Integer> entry : enchs.entrySet()) {
				if(ench1 == null) {
					ench1 = new Pair<Enchantment, Integer>(entry.getKey(), entry.getValue());
				}else {
					ench2 = new Pair<Enchantment, Integer>(entry.getKey(), entry.getValue());
				}
			}
		}
		
		if(ench1.GetItem1() != ench2.GetItem1())
			e.setCancelled(true);
		
		if(ench1.GetItem2() != ench2.GetItem2())
			e.setCancelled(true);
		
		ItemStack result = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta meta = result.getItemMeta();
		
		meta.addEnchant(ench1.GetItem1(), ench1.GetItem2() + 1, true);
		
		result.setItemMeta(meta);
		
		e.getInventory().setResult(result);
	}
}
