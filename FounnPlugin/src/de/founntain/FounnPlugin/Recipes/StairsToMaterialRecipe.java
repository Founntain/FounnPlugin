package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class StairsToMaterialRecipe {
	private Plugin plugin;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public StairsToMaterialRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering StairsToMaterialRecipe...");
		this.plugin = plugin;
		this.createRecipes();
	}
	
	private void createRecipes() {
		Material[] materials = new Material[] {
				Material.ACACIA_STAIRS,
				Material.ANDESITE_STAIRS,
				Material.BIRCH_STAIRS,
				Material.BLACKSTONE_STAIRS,
				Material.BRICK_STAIRS,
				Material.COBBLESTONE_STAIRS,
				Material.CRIMSON_STAIRS,
				Material.DARK_OAK_STAIRS,
				Material.DARK_PRISMARINE_STAIRS,
				Material.DIORITE_STAIRS,
				Material.END_STONE_BRICK_STAIRS,
				Material.GRANITE_STAIRS,
				Material.JUNGLE_STAIRS,
				Material.MOSSY_COBBLESTONE_STAIRS,
				Material.MOSSY_STONE_BRICK_STAIRS,
				Material.NETHER_BRICK_STAIRS,
				Material.OAK_STAIRS,
				Material.POLISHED_ANDESITE_STAIRS,
				Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
				Material.POLISHED_BLACKSTONE_STAIRS,
				Material.POLISHED_DIORITE_STAIRS,
				Material.POLISHED_GRANITE_STAIRS,
				Material.PRISMARINE_BRICK_STAIRS,
				Material.PRISMARINE_STAIRS,
				Material.PURPUR_STAIRS,
				Material.QUARTZ_STAIRS,
				Material.RED_NETHER_BRICK_STAIRS,
				Material.RED_SANDSTONE_STAIRS,
				Material.SANDSTONE_STAIRS,
				Material.SMOOTH_QUARTZ_STAIRS,
				Material.SMOOTH_RED_SANDSTONE_STAIRS,
				Material.SPRUCE_STAIRS,
				Material.STONE_BRICK_STAIRS,
				Material.STONE_STAIRS,
				Material.WARPED_STAIRS
		};
		
		Material[] outputs = new Material[] {
				Material.ACACIA_PLANKS,
				Material.ANDESITE,
				Material.BIRCH_PLANKS,
				Material.BLACKSTONE,
				Material.BRICKS,
				Material.COBBLESTONE,
				Material.CRIMSON_PLANKS,
				Material.DARK_OAK_PLANKS,
				Material.DARK_PRISMARINE,
				Material.DIORITE,
				Material.END_STONE,
				Material.GRANITE,
				Material.JUNGLE_PLANKS,
				Material.MOSSY_COBBLESTONE,
				Material.MOSSY_STONE_BRICKS,
				Material.NETHER_BRICKS,
				Material.OAK_PLANKS,
				Material.POLISHED_ANDESITE,
				Material.POLISHED_BLACKSTONE_BRICKS,
				Material.POLISHED_BLACKSTONE,
				Material.POLISHED_DIORITE,
				Material.POLISHED_GRANITE,
				Material.PRISMARINE_BRICKS,
				Material.PRISMARINE,
				Material.PURPUR_BLOCK,
				Material.QUARTZ_BLOCK,
				Material.RED_NETHER_BRICKS,
				Material.RED_SANDSTONE,
				Material.SANDSTONE,
				Material.SMOOTH_QUARTZ,
				Material.SMOOTH_RED_SANDSTONE,
				Material.SPRUCE_PLANKS,
				Material.STONE_BRICKS,
				Material.STONE,
				Material.WARPED_PLANKS
		};
		
		for(int i = 0; i <= outputs.length - 1; i++) {
			ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "custom" + outputs[i].toString() + "StairConverterRecipe"), new ItemStack(outputs[i], 6));
			
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			
			this.plugin.getServer().addRecipe(shapelessRecipe);
		}
	}
}
