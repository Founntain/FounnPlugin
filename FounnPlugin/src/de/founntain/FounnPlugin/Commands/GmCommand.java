package de.founntain.FounnPlugin.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(args.length == 0)
				return false;
			
			int mode = Integer.parseInt(args[0]);
			
			switch(mode) {
				case 1:
					if(!player.isOp())
						return false;
					
					player.setGameMode(GameMode.CREATIVE);
					break;
				
				case 2:
					player.setGameMode(GameMode.ADVENTURE);
					break;
				case 3:
					if(!player.isOp())
						return false;
					
					player.setGameMode(GameMode.SPECTATOR);
					break;
				default:
					player.setGameMode(GameMode.SURVIVAL);
					break;
			}
			
		}
		return true;
	}

}
