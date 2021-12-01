package dev.founntain.founnplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.founntain.founnplugin.classes.Utilities;
import dev.founntain.founnplugin.guis.SendItemGui;

public class SendItemCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			if(args.length == 0) {
				return true;
			}
			
			Player player = (Player) sender;
			Player receiver = Bukkit.getPlayer(args[0]);
			
			if(receiver == null) {
				player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + "Der Spieler konnte nicht gefunden werden!");
				return true;
			}
			
			if(Utilities.getUsedInventorySlotsCount(player.getInventory()) == 0) {
				player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + "Dein Inventar ist leer also kannst du auch nichts an " + receiver.getDisplayName() + " senden!");
				return true;
			}
			
			if(receiver.getInventory().firstEmpty() == -1) {
				player.sendMessage(Utilities.getErrorPrefix() + ChatColor.RED + "Das Inventar von " + receiver.getDisplayName() + " ist voll, der Vorgang wird abgebrochen!");
				return true;
			}
			
			SendItemGui sendItemToPlayerGui = new SendItemGui(player, receiver);
			sendItemToPlayerGui.openSendItemToPlyerGui();
		}
		
		return true;
	}
}
