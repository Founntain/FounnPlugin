package de.founntain.FounnPlugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.founntain.FounnPlugin.FounnPlugin;
import de.founntain.FounnPlugin.CustomMobs.FreedomfighterMob;

public class SpawnCustomMobCommand implements CommandExecutor{
	
	private Plugin plugin;
	
	public SpawnCustomMobCommand(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!player.getUniqueId().equals(FounnPlugin.FounntainUUID))
				return true;
			
			if(args.length != 2) {
				return true;
			}
				
			switch(args[1].toLowerCase()) {
				case "boomSpawnerZombie":
					
					Player target = (Player) Bukkit.getPlayer(args[0]);
					
					Location loc = target.getLocation();
					
					loc.setY(loc.getY() + 3);
					
					FreedomfighterMob freedomfighterMob = new FreedomfighterMob(this.plugin);
					freedomfighterMob.spawnFreedomFighter(loc, target);
					
					return true;
				default:
					return true;
			}
		}
		
		return true;
	}
}
