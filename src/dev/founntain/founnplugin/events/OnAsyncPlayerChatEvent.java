package dev.founntain.founnplugin.events;

import dev.founntain.founnplugin.FounnPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnAsyncPlayerChatEvent implements Listener{
	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		String format = "";
		
		switch(e.getPlayer().getUniqueId().toString()) {
			case "1f146f64-96fb-400c-971a-8d68e7d96b69":
				format = "[" + ChatColor.DARK_PURPLE + "Owner" + ChatColor.WHITE + "] %s » " + ChatColor.GOLD + "%s";
				break;
			case "919d6bbb-e5bd-4210-89c9-70ff8183f970":
				format = "[" + ChatColor.LIGHT_PURPLE + "René" + ChatColor.WHITE + "] %s » " + ChatColor.WHITE + "%s";
				break;
			case "3a3a4d71-08c8-4dc2-a7e2-433c61fda42b":
				format = "[" + ChatColor.DARK_GRAY + "?" + ChatColor.WHITE + "] %s » " + ChatColor.WHITE + "%s";
				break;
			case "312da023-8817-4609-9b24-7d74bf5252f7":
				format = "[" + ChatColor.GOLD + "Katze" + ChatColor.WHITE + "] %s » " + ChatColor.WHITE + "%s";
				break;
			default:
				format = "[" + ChatColor.YELLOW + "Spieler" + ChatColor.WHITE + "] %s » " + ChatColor.WHITE + "%s";
				break;
		}
		
		e.setFormat(format);
	}
}
