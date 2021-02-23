package de.founntain.FounnPlugin.Recipes;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class FireFleshToLeatherRecipe {
	private Plugin plugin;
	private String prefix = ChatColor.WHITE + "[" + ChatColor.LIGHT_PURPLE + "R" + ChatColor.WHITE + "] " + ChatColor.AQUA;
	
	public FireFleshToLeatherRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering FiredFleshRecipe...");
		this.plugin = plugin;
		this.createRecipe();
	}
	
	public void createRecipe() {
		//FurnaceRecipe furnaceRecipe = new FurnaceRecipe(new ItemStack(Material.LEATHER)
		
	}
}
