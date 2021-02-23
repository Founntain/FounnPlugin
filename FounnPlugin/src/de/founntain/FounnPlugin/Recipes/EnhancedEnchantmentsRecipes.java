package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class EnhancedEnchantmentsRecipes {

	private Plugin plugin;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public EnhancedEnchantmentsRecipes(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering EnhancedEnchantmentsRecipes...");
		this.plugin = plugin;
		this.createRecipes();
	}
	
	private void createRecipes() {
		
		/*
		for(Enchantment ench : enchantments) {
			for(int i = 1; i <= 10; i++) {
				
				ItemStack baseBook = new ItemStack(Material.ENCHANTED_BOOK);
				ItemMeta meta = baseBook.getItemMeta();
				meta.addEnchant(ench, i, false);
				
				MaterialData data = baseBook.getData();
				
				ItemStack result = new ItemStack(Material.ENCHANTED_BOOK);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.addEnchant(ench, i + 1, false);
				
				ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "custom" + ench.toString() + "Lvl" + i + "Recipe"), result);
				shapelessRecipe
					.addIngredient(data)
					.addIngredient(data);
				
				this.plugin.getServer().addRecipe(shapelessRecipe);
			}
		}
		*/
		
		ItemStack result = new ItemStack(Material.ENCHANTED_BOOK);
		ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "customEnchantmentRecipe"), result);
		shapelessRecipe.addIngredient(Material.ENCHANTED_BOOK).addIngredient(Material.ENCHANTED_BOOK);
		
		this.plugin.getServer().addRecipe(shapelessRecipe);
	}
}
