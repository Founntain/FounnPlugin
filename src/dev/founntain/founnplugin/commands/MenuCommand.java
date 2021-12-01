package dev.founntain.founnplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.founntain.founnplugin.guis.MenuGui;

public class MenuCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;

			MenuGui menuGui = new MenuGui(player);
			
			menuGui.openMenuGui();
		}
		
		return true;
	}
}
