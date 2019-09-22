package de.founntain.FounnPlugin;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
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
				ChatColor.GRAY + " Spieler Online: " + ChatColor.DARK_PURPLE + (Bukkit.getOnlinePlayers().size() - 1) + " ";
	}
	
	public static Color getColor(int i) {
		switch(i) {
			case 1:
				return Color.AQUA;
			case 2:
				return Color.BLACK;
			case 3:
				return Color.BLUE;
			case 4:
				return Color.FUCHSIA;
			case 5:
				return Color.GRAY;
			case 6:
				return Color.GREEN;
			case 7:
				return Color.LIME;
			case 8:
				return Color.MAROON;
			case 9:
				return Color.NAVY;
			case 10:
				return Color.OLIVE;
			case 11:
				return Color.ORANGE;
			case 12:
				return Color.PURPLE;
			case 13:
				return Color.RED;
			case 14:
				return Color.SILVER;
			case 15:
				return Color.TEAL;
			case 16:
				return Color.WHITE;
			default:
				return Color.YELLOW;
		}
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
        
        Color c1 = Utilities.getColor(rdm.nextInt(17) + 1);
        Color c2 = Utilities.getColor(rdm.nextInt(17) + 1);
        
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
}
