package dev.founntain.founnplugin.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class FiredFleshRecipe extends BaseRecipe{
	private Plugin plugin;
	
	public FiredFleshRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering " + this.getClass().getSimpleName());
		this.plugin = plugin;
		
		this.createRecipe();
	}
	
	public void createRecipe() {
		ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "rottenFleshToLeather"), new ItemStack(Material.LEATHER));
		
		recipe.addIngredient(Material.ROTTEN_FLESH);
		recipe.addIngredient(Material.BLAZE_POWDER);
		
		plugin.getServer().addRecipe(recipe);
	}
}
