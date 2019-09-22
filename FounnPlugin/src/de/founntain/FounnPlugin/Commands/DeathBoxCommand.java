package de.founntain.FounnPlugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.founntain.FounnPlugin.DeathItems;
import de.founntain.FounnPlugin.Utilities;

public class DeathBoxCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(!DeathItems.Items.containsKey(player.getUniqueId())) {
				player.sendMessage(Utilities.getErrorPrefix() + "Konnte keine Deathbox finden!");
			}
			
			DeathItems.showDeathItems(player.getUniqueId());
		}
		
		return true;
	}
}
