package de.founntain.FounnPlugin.Recipes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class TntPickaxe {
	private Plugin plugin;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public TntPickaxe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering FiredFleshRecipe...");
		this.plugin = plugin;
		this.createRecipe();
	}
	
	public void createRecipe() {
		ItemStack tntPickaxe = new ItemStack(Material.GOLDEN_PICKAXE);
		ItemMeta meta = tntPickaxe.getItemMeta();
		
		meta.setDisplayName(ChatColor.GOLD + "TNT Pickaxe");
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Use it on a block and wait " + ChatColor.GOLD + "5" + ChatColor.GRAY + " seconds ");
		meta.setLore(lore);
		
		tntPickaxe.setItemMeta(meta);
		
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this.plugin, "founnplugin_TNT_PICKAXE"), tntPickaxe);
		
		recipe.shape("TTT", "XSX", "XSX");
		
		recipe.setIngredient('T', Material.TNT);
		recipe.setIngredient('S', Material.STICK);
		recipe.setIngredient('X', Material.AIR);
		
		plugin.getServer().addRecipe(recipe);
	}
}