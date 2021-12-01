package dev.founntain.founnplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import dev.founntain.founnplugin.FounnPlugin;
import dev.founntain.founnplugin.custom_mobs.FireworkCreeperMob;
import dev.founntain.founnplugin.custom_mobs.FreedomfighterMob;

public class SpawnCustomMobCommand implements CommandExecutor{
	
	private Plugin plugin;
	
	public SpawnCustomMobCommand(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!player.getUniqueId().equals(FounnPlugin.founntainUUID))
				return true;
			
			if(args.length != 2) {
				return true;
			}
			
			Player target = (Player) Bukkit.getPlayer(args[0]);
			Location loc = target.getLocation();
				
			switch(args[1].toLowerCase()) {
				case "boomspawnerzombie":
					loc.setY(loc.getY() + 3);
					
					FreedomfighterMob freedomfighterMob = new FreedomfighterMob(this.plugin);
					freedomfighterMob.spawnFreedomFighter(loc, target);
					
					return true;
				case "silversterparty":
					loc.setY(loc.getY() + 3);
					
					FireworkCreeperMob fireworkCreeperMob = new FireworkCreeperMob(this.plugin);
					fireworkCreeperMob.spawnMob(loc, target);
				default:
					return true;
			}
		}
		
		return true;
	}
}
