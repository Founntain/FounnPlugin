package de.founntain.founnplugin.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.founntain.founnplugin.classes.Utilities;
import net.md_5.bungee.api.ChatColor;

public class DayCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player)sender;

			World world = player.getWorld();
			world.setTime(1000);
			
			for (Player p : world.getPlayers()) {
				p.sendMessage(Utilities.getCustomPrefix(ChatColor.YELLOW, "W") + player.getName() +
						" made it day (1000) in " + world.getName() + "!");
			}
		}
		
		return true;
	}
}
