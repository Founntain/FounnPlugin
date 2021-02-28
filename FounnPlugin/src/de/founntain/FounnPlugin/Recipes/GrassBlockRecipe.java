package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class GrassBlockRecipe extends BaseRecipe{
	private Plugin plugin;
	
	public GrassBlockRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering " + this.getClass().getSimpleName());
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
