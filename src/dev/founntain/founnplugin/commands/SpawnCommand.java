package dev.founntain.founnplugin.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			Location worldSpawn = player.getWorld().getSpawnLocation();
			Location bedSpawn = player.getBedSpawnLocation();
			
			player.teleport(bedSpawn == null ? worldSpawn : bedSpawn);
			
			return true;
		}
		
		return false;
	}

}
