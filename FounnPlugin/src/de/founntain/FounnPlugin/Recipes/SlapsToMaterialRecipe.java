package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class SlapsToMaterialRecipe {
	private Plugin plugin;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public SlapsToMaterialRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering SlapsToMaterialRecipe...");
		this.plugin = plugin;
		this.createRecipes();
	}
	
	private void createRecipes() {
		Material[] materials = new Material[] {
				Material.ACACIA_SLAB,
				Material.ANDESITE_SLAB,
				Material.BIRCH_SLAB,
				Material.BLACKSTONE_SLAB,
				Material.BRICK_SLAB,
				Material.COBBLESTONE_SLAB,
				Material.CRIMSON_SLAB,
				Material.DARK_OAK_SLAB,
				Material.DARK_PRISMARINE_SLAB,
				Material.DIORITE_SLAB,
				Material.END_STONE_BRICK_SLAB,
				Material.GRANITE_SLAB,
				Material.JUNGLE_SLAB,
				Material.MOSSY_COBBLESTONE_SLAB,
				Material.MOSSY_STONE_BRICK_SLAB,
				Material.NETHER_BRICK_SLAB,
				Material.OAK_SLAB,
				Material.POLISHED_ANDESITE_SLAB,
				Material.POLISHED_BLACKSTONE_BRICK_SLAB,
				Material.POLISHED_BLACKSTONE_SLAB,
				Material.POLISHED_DIORITE_SLAB,
				Material.POLISHED_GRANITE_SLAB,
				Material.PRISMARINE_BRICK_SLAB,
				Material.PRISMARINE_SLAB,
				Material.PURPUR_SLAB,
				Material.QUARTZ_SLAB,
				Material.RED_NETHER_BRICK_SLAB,
				Material.RED_SANDSTONE_SLAB,
				Material.SANDSTONE_SLAB,
				Material.SMOOTH_QUARTZ_SLAB,
				Material.SMOOTH_RED_SANDSTONE_SLAB,
				Material.SMOOTH_STONE_SLAB,
				Material.SPRUCE_SLAB,
				Material.STONE_BRICK_SLAB,
				Material.STONE_SLAB,
				Material.WARPED_SLAB
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
				Material.QUARTZ,
				Material.RED_NETHER_BRICKS,
				Material.RED_SANDSTONE,
				Material.SANDSTONE,
				Material.SMOOTH_QUARTZ,
				Material.SMOOTH_RED_SANDSTONE,
				Material.SMOOTH_STONE,
				Material.SPRUCE_PLANKS,
				Material.STONE_BRICKS,
				Material.STONE,
				Material.WARPED_PLANKS
		};
		
		for(int i = 0; i <= outputs.length - 1; i++) {
			ShapelessRecipe shapelessRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "custom" + outputs[i].toString() + "SlapConverterRecipe"), new ItemStack(outputs[i], 3));
			
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			shapelessRecipe.addIngredient(materials[i]);
			
			this.plugin.getServer().addRecipe(shapelessRecipe);
		}
	}
}
