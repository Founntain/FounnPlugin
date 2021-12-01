package dev.founntain.founnplugin.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

public class SlapsToMaterialRecipe extends BaseRecipe{
	private Plugin plugin;
	
	public SlapsToMaterialRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering " + this.getClass().getSimpleName());
		this.plugin = plugin;
		
		this.createRecipes();
	}
	
	private void createRecipes() {
		Iterator<Recipe> recipes = Bukkit.recipeIterator();
		HashMap<Material, Material> combinations = new HashMap<Material, Material>(); 
		
		while(recipes.hasNext()){
			Recipe r = recipes.next();
			
			if(r instanceof ShapedRecipe) {
				ShapedRecipe sr = (ShapedRecipe) r;
				
				if(Tag.STAIRS.isTagged(sr.getResult().getType())) {
					for(Entry<Character, ItemStack> entry : sr.getIngredientMap().entrySet()) {
						if(combinations.containsKey(sr.getResult().getType())) continue;
						
						combinations.put(sr.getResult().getType(), entry.getValue().getType());
					}
				}
			}
		}
		
		for(Entry<Material, Material> entry: combinations.entrySet()) {
			ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "custom" + entry.getKey().toString() + "SlabsConverterRecipe"), new ItemStack(entry.getValue(), 6));
			
			for(int i = 0; i < 6; i++)
				shapelessRecipe.addIngredient(entry.getKey());
			
			this.plugin.getServer().addRecipe(shapelessRecipe);
		}
	}
}