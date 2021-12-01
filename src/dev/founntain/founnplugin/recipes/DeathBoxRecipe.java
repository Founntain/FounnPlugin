package dev.founntain.founnplugin.recipes;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class DeathBoxRecipe extends BaseRecipe{
	private Plugin plugin;
	
	public DeathBoxRecipe(Plugin plugin) {
		Bukkit.getConsoleSender().sendMessage(this.prefix + "registering " + this.getClass().getSimpleName());
		this.plugin = plugin;
		
		this.createRecipe();
	}
	
	private void createRecipe() {
		ItemStack deathBox = new ItemStack(Material.CHEST);
		
		ItemMeta deathBoxMeta = deathBox.getItemMeta();
		deathBoxMeta.setDisplayName(ChatColor.DARK_PURPLE + "Deathbox");
		
		ArrayList<String> deathBoxLore = new ArrayList<String>();
		deathBoxLore.add(ChatColor.GRAY + "Wenn du die Box im Inventar hast");
		deathBoxLore.add(ChatColor.GRAY + "und sterben solltest, dann");
		deathBoxLore.add(ChatColor.GRAY + "findest du am Todespunkt eine "+ ChatColor.DARK_PURPLE + "Kiste" + ChatColor.GRAY + "!");
		
		deathBoxMeta.setLore(deathBoxLore);
		deathBox.setItemMeta(deathBoxMeta);
		
		ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(this.plugin, "deathBoxRecipe"), deathBox);
		shapedRecipe.shape("DDD", "DGD", "DDD");
		shapedRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
		shapedRecipe.setIngredient('G', Material.GOLDEN_APPLE);
		
		this.plugin.getServer().addRecipe(shapedRecipe);
	}
}
