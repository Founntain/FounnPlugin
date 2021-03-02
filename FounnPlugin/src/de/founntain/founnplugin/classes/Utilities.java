package de.founntain.founnplugin.classes;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Utilities {
	public static String getFormatedEnvironmentName(Environment environment) {
		switch (environment) {
		case NETHER:
			return ChatColor.LIGHT_PURPLE + "Nether";
		case THE_END:
			return ChatColor.DARK_PURPLE + "The End";
		default:
			return ChatColor.GREEN + "Overworld";
		}
	}
	
	public static int getEmptyInventorySlotsCount(Inventory inventory) {
		int i = 0;
		for(ItemStack is : inventory.getContents()) {
			if(is != null)
				continue;
			
			i++;
		}
		
		return i;
	}
	
	public static int getUsedInventorySlotsCount(Inventory inventory) {
		int i = 0;
		for(ItemStack is : inventory.getContents()) {
			if(is == null)
				continue;
			i++;
		}
		
		return i;
	}
	
	public static String getErrorPrefix() {
		return ChatColor.WHITE + "[" + ChatColor.RED + "E" + ChatColor.WHITE +"] ";
	}
	
	public static String getCustomPrefix(ChatColor letterColor, String letter) {
		return ChatColor.WHITE + "[" + letterColor + letter.toUpperCase() + ChatColor.WHITE +"] ";
	}
	
	public static ItemStack createMenuItem(Material material, String label) {
		ItemStack item = new ItemStack(material);
		
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(label);
		
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static ItemStack createMenuItem(Material material, String label, String[] description) {
		ItemStack item = new ItemStack(material);
		
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(label);
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		for(String s : description)
			arrayList.add(s);
		
		itemMeta.setLore(arrayList);
		
		item.setItemMeta(itemMeta);
		
		return item;
	}
	
	public static String getTablistHeader() {
		return ChatColor.YELLOW + " Willkommen auf dem Server! \n\n";
	}
	
	public static String getTablistFooter() {
		return ChatColor.GRAY + "\n Mit " + ChatColor.YELLOW + "/menu" + ChatColor.GRAY + " kanns du das Menu öffnen! \n" +
				ChatColor.GRAY + " Spieler Online: " + ChatColor.DARK_PURPLE + (Bukkit.getOnlinePlayers().size()) + " ";
	}
	
	public static Color getRandomBGRColor() {
		Random random = new Random();
	    return Color.fromBGR(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	public static void spawnRandomFirework(Location location) {
		Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
		
		FireworkMeta fireworkMeta = firework.getFireworkMeta();
		
		Random rdm = new Random();
		
		fireworkMeta.setPower(rdm.nextInt(3));
		
        int randomType = rdm.nextInt(4) + 1;
        Type type = Type.BALL;
        
        switch(randomType) {
        	case 2:
        		type = Type.BALL_LARGE;
        		break;
        	case 3:
        		type = Type.BURST;
        		break;
        	case 4:
        		type = Type.STAR;
        		break;
        	default:
        		type = Type.BALL;
        		break;
        }
        
        Color c1 = Utilities.getRandomBGRColor();
        Color c2 = Utilities.getRandomBGRColor();
        
        FireworkEffect effect = FireworkEffect.builder().flicker(
        		rdm.nextBoolean())
        		.withColor(c1)
        		.withFade(c2)
        		.with(type)
        		.trail(rdm.nextBoolean()
    		).build();
       
        //Then apply the effect to the meta
        fireworkMeta.addEffect(effect);
        
        firework.setFireworkMeta(fireworkMeta);
	}
	
	public static boolean CheckIfBlockIsOre(Material material) {
		switch(material) {
			case COAL_ORE:
			case IRON_ORE:
			case GOLD_ORE:
			case LAPIS_ORE:
			case REDSTONE_ORE:
			case DIAMOND_ORE:
			case EMERALD_ORE:
			case NETHER_QUARTZ_ORE:
				return true;
			default:
				return false;
		}
	}
	
	public static ItemStack GetDropItem(Material material) {
		
		Random rdm = new Random();
		
		switch(material) {
			case COAL_ORE:
				return new ItemStack(Material.COAL);
			case IRON_ORE:
				return new ItemStack(Material.IRON_ORE);
			case GOLD_ORE:
				return new ItemStack(Material.GOLD_ORE);
			case LAPIS_ORE:
				return new ItemStack(Material.LAPIS_LAZULI, rdm.nextInt(5) + 4);
			case REDSTONE_ORE:
				return new ItemStack(Material.REDSTONE, rdm.nextInt(5) + 4);
			case DIAMOND_ORE:
				return new ItemStack(Material.DIAMOND);
			case EMERALD_ORE:
				return new ItemStack(Material.EMERALD);
			case NETHER_QUARTZ_ORE:
				return new ItemStack(Material.QUARTZ);
			default:
				return null;
		}
	}
	
	public static ArrayList<Pair<Enchantment, Integer>> GetEnchantmentsFromItem(ItemStack item) {
		ArrayList<Pair<Enchantment, Integer>> enchantments = new ArrayList<Pair<Enchantment, Integer>>();
		
		EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
		
		int storedCount = meta.getStoredEnchants().keySet().size();
		int count = meta.getEnchants().keySet().size();
		
		if(storedCount == 0 && count == 0)
			return enchantments;
		
		Map<Enchantment, Integer> enchs = null;
		
		if(storedCount > 0)
			enchs = meta.getStoredEnchants();
		else if(count > 0)
			enchs = meta.getEnchants();
		
		for(Map.Entry<Enchantment, Integer> entry : enchs.entrySet()) {
			enchantments.add(new Pair<Enchantment, Integer>(entry.getKey(), entry.getValue()));
		}
		
		return enchantments;
	}
}
