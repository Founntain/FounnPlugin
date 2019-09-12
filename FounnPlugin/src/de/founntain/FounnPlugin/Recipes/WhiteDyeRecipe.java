package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class WhiteDyeRecipe {
	
	private Plugin plugin;
	private String prexix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public WhiteDyeRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prexix + "registering WhiteDyeRecipe...");
		this.plugin = plugin;
		this.createRecipe();
	}
	
	private void createRecipe() {
		ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(plugin, "customWhiteDye"), new ItemStack(Material.WHITE_DYE));
		
		recipe.addIngredient(Material.OXEYE_DAISY);
		recipe.addIngredient(Material.OXEYE_DAISY);
		
		plugin.getServer().addRecipe(recipe);
	}
}
