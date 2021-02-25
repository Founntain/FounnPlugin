package de.founntain.FounnPlugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.founntain.FounnPlugin.Utilities.DeathCoord;
import de.founntain.FounnPlugin.Utilities.PlayerTeleportCoords;

public class BackCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			Location lastTeleportLocation = PlayerTeleportCoords.playerTeleportCoords.get(player.getUniqueId());
			
			if(lastTeleportLocation == null) {
				player.sendMessage(ChatColor.RED + "Leider konnte der Server deine letzte position nicht finden!");
				return true;
			}
			
			player.teleport(lastTeleportLocation);
			
			DeathCoord.deathCoords.remove(player.getUniqueId());
			
			player.sendMessage(ChatColor.GREEN + "Du wurdest auf die angegebene Position teleportiert.");
		}
			
		return true;
	}
}
