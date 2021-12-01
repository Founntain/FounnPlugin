package dev.founntain.founnplugin.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(args.length != 1)
			return false;
		
		String weatherArg = args[0];
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!player.isOp()) return false;
			
			World world = player.getWorld();
			
			switch(weatherArg) {
				case "c":
					world.setStorm(false);
					world.setThundering(false);
					world.setClearWeatherDuration(12000);
					
					break;
				case "r":
					world.setStorm(true);
					world.setThundering(false);
					world.setWeatherDuration(12000);
					
					break;
				case "t":
					world.setStorm(true);
					world.setThundering(true);
					world.setWeatherDuration(12000);
					world.setThunderDuration(12000);
					
					break;
				default:
					return false;
			}
		}
			
		return true;
	}
}
