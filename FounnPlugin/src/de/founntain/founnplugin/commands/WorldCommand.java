package de.founntain.founnplugin.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.founntain.founnplugin.FounnPlugin;
import de.founntain.founnplugin.WorldGenerator;
import de.founntain.founnplugin.classes.Utilities;
import net.md_5.bungee.api.ChatColor;

public class WorldCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		
		int argsCount = args.length;
		
		Player player = (Player) sender;

		if(argsCount == 0) {
			this.runDefaultCommand(player);
			return true;
		}
		
		String option = args[0];
		
		switch(option) {
			case "create":
				this.createOption(argsCount, args, player);
				break;
			case "delete":
				if(!player.getUniqueId().equals(FounnPlugin.founntainUUID)) {
					player.sendMessage(Utilities.getCustomPrefix(ChatColor.RED, "E") + "Du hast keine Rechte diesen Befehl zu nutzten!");
					return false;
				}
				
				break;
			default:
				return true;
		}
		
		return true;
	}
	
	private void runDefaultCommand(final Player player) {
		Inventory inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GREEN + "World list");
		
		for(final World world : Bukkit.getServer().getWorlds()) {
			ItemStack worldItem = null;
			
			if(world.getName().equals("world_nether") || world.getName().equals("world_the_end")) continue;
			
			switch(world.getEnvironment()) {
				case NORMAL:
					worldItem = new ItemStack(Material.GRASS_BLOCK);
					break;
				case NETHER:
					worldItem = new ItemStack(Material.NETHERRACK);
					break;
				case THE_END:
					worldItem = new ItemStack(Material.END_STONE);
					break;
				default: 
					worldItem = new ItemStack(Material.GRASS_BLOCK);
					break;
			}
			
			ItemMeta meta = worldItem.getItemMeta();
			
			meta.setDisplayName(ChatColor.YELLOW + world.getName());
			
			ArrayList<String> lore = new ArrayList<String>();
			
			lore.add(ChatColor.GREEN + "Seed: " + world.getSeed());
			
			long time = world.getTime();
			int hours = (int) ((Math.floor(time / 1000.0) + 8) % 24);
		    int minutes = (int) Math.floor((time % 1000) / 1000.0 * 60);
			
			lore.add(ChatColor.GREEN + "Time: " + hours + ":"+ minutes);

			meta.setLore(lore);
			
			worldItem.setItemMeta(meta);
			
			inv.addItem(worldItem);
		}
		
		player.openInventory(inv);
	}
	
	private boolean createOption(final int argsCount, final String[] args, final Player player) {
		String worldname = null;
		String environment = null;
		String type = null;
		
		if(!player.getUniqueId().equals(FounnPlugin.founntainUUID)) {
			player.sendMessage(Utilities.getCustomPrefix(ChatColor.RED, "E") + "Du hast keine Rechte diesen Befehl zu nutzten!");
			return false;
		}
		
		switch(argsCount) {
			case 2:
				worldname = args[1];
				
				this.createWorldWithParameters(player, worldname, Environment.NORMAL, WorldType.NORMAL);
				break;
			case 3:
				worldname = args[1];
				environment = args[2];
				
				this.createWorldWithParameters(player, worldname, this.getEnvironmentFromString(environment), WorldType.NORMAL);
				break;
			case 4:
				worldname = args[1];
				environment = args[2];
				type = args[3];
				
				this.createWorldWithParameters(player, worldname, this.getEnvironmentFromString(environment), this.getWorldTypeFromString(type));
				break;
			default:
				return false;
		}
		
		return true;
	}
	
	private void createWorldWithParameters(final Player player, final String worldname, final Environment env, final WorldType type) {
		WorldGenerator wg = new WorldGenerator(worldname, player);
		
		wg.generateWorld(env, type);
	}
	
	private Environment getEnvironmentFromString(String env) {
		env = env.toLowerCase();
		
		switch(env) {
			case "normal":
				return Environment.NORMAL;
			case "nether":
				return Environment.NETHER;
			case "end":
				return Environment.THE_END;
			default:
				return Environment.NORMAL;
		}
	}
	
	private WorldType getWorldTypeFromString(String type) {
		type = type.toLowerCase();
		
		switch(type) {
			case "normal":
				return WorldType.NORMAL;
			case "largebiomes":
				return WorldType.LARGE_BIOMES;
			case "amplified":
				return WorldType.AMPLIFIED;
			default:
				return WorldType.NORMAL;
		}
	}
}
