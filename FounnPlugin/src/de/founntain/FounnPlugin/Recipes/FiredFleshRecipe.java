package de.founntain.FounnPlugin.Recipes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class FiredFleshRecipe {
	private Plugin plugin;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public FiredFleshRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering FiredFleshRecipe...");
		this.plugin = plugin;
		this.createRecipe();
	}
	
	public void createRecipe() {
		ItemStack firedFlesh = new ItemStack(Material.ROTTEN_FLESH);
		ItemMeta meta = firedFlesh.getItemMeta();
		
		meta.setDisplayName(ChatColor.GOLD + "Fired Flesh");
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Put it into a furnace to get "+ ChatColor.GOLD +"Leather!");
		meta.setLore(lore);
		
		firedFlesh.setItemMeta(meta);
		
		ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(this.plugin, "firedFleshRecipe"), new ItemStack(Material.LEATHER));
		
		recipe.addIngredient(Material.ROTTEN_FLESH);
		recipe.addIngredient(Material.BLAZE_POWDER);
		
		plugin.getServer().addRecipe(recipe);
	}
}
