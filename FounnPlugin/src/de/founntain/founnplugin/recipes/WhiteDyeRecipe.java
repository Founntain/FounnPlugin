package de.founntain.founnplugin.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class WhiteDyeRecipe extends BaseRecipe{
	
	private Plugin plugin;
	
	public WhiteDyeRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering " + this.getClass().getSimpleName());
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
