package de.founntain.founnplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.founntain.founnplugin.classes.DeathItems;
import de.founntain.founnplugin.classes.Utilities;

public class DeathBoxCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!DeathItems.items.containsKey(player.getUniqueId())) {
				player.sendMessage(Utilities.getErrorPrefix() + "Konnte keine Deathbox finden!");
			}
			
			DeathItems.showDeathItems(player.getUniqueId());
		}
		
		return true;
	}
}
