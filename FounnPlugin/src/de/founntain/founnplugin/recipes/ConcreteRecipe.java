package de.founntain.founnplugin.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class ConcreteRecipe extends BaseRecipe{
	private Plugin plugin;
	
	public ConcreteRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering " + this.getClass().getSimpleName());
		this.plugin = plugin;
		
		this.createRecipes();
	}
	
	private void createRecipes() {
		
		Material[] concretes = new Material[] {
			Material.WHITE_CONCRETE,
			Material.ORANGE_CONCRETE,
			Material.MAGENTA_CONCRETE,
			Material.LIGHT_BLUE_CONCRETE,
			Material.YELLOW_CONCRETE,
			Material.LIME_CONCRETE,
			Material.PINK_CONCRETE,
			Material.GRAY_CONCRETE,
			Material.LIGHT_GRAY_CONCRETE,
			Material.CYAN_CONCRETE,
			Material.PURPLE_CONCRETE,
			Material.BLUE_CONCRETE,
			Material.BROWN_CONCRETE,
			Material.GREEN_CONCRETE,
			Material.RED_CONCRETE,
			Material.BLACK_CONCRETE
		};
		
		Material[] concretePowders = new Material[] {
			Material.WHITE_CONCRETE_POWDER,
			Material.ORANGE_CONCRETE_POWDER,
			Material.MAGENTA_CONCRETE_POWDER,
			Material.LIGHT_BLUE_CONCRETE_POWDER,
			Material.YELLOW_CONCRETE_POWDER,
			Material.LIME_CONCRETE_POWDER,
			Material.PINK_CONCRETE_POWDER,
			Material.GRAY_CONCRETE_POWDER,
			Material.LIGHT_GRAY_CONCRETE_POWDER,
			Material.CYAN_CONCRETE_POWDER,
			Material.PURPLE_CONCRETE_POWDER,
			Material.BLUE_CONCRETE_POWDER,
			Material.BROWN_CONCRETE_POWDER,
			Material.GREEN_CONCRETE_POWDER,
			Material.RED_CONCRETE_POWDER,
			Material.BLACK_CONCRETE_POWDER
		};
		
		for(int i = 0; i <= concretes.length - 1; i++) {
			ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "custom" + concretes[i].toString() + "Recipe"), new ItemStack(concretes[i]));
			shapelessRecipe.addIngredient(concretePowders[i]);
			
			this.plugin.getServer().addRecipe(shapelessRecipe);
		}
	}
}
