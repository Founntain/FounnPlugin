package de.founntain.FounnPlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCoords implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Location loc = player.getLocation();
			
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();
			
			if(args.length > 0) {
				if(args[0].equals("nether")) {
					
					if(player.getWorld().getEnvironment() != Environment.NORMAL) {
						player.sendMessage(ChatColor.RED + "Du kannst den Befehl nur in der Overworld benutzen!");
						return false;
					}
					
					player.sendMessage(ChatColor.LIGHT_PURPLE + "Deine Netherportal Koordinaten: " + ChatColor.YELLOW + (int)(x/8) + ", " + (int)y + ", " + (int)(z/8));
				}
			}else {
				player.chat(ChatColor.YELLOW + "Meine Koordinaten: " + x + ", " + y + ", " + z);
			}
			
			return true;
		}
		
		return false;
	}
}
