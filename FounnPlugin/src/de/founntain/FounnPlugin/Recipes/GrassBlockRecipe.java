package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class GrassBlockRecipe {
	private Plugin plugin;
	private String prexix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public GrassBlockRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prexix + "registering ConcreteRecipes...");
		this.plugin = plugin;
		this.createRecipe();
	}
	
	public void createRecipe() {
		ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "customGrassBlockRecipe"), new ItemStack(Material.GRASS_BLOCK));
		shapelessRecipe.addIngredient(Material.DIRT);
		shapelessRecipe.addIngredient(Material.WHEAT_SEEDS);
		
		this.plugin.getServer().addRecipe(shapelessRecipe);
	}
}
