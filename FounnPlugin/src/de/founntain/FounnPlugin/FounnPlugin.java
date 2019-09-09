package de.founntain.FounnPlugin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import de.founntain.FounnPlugin.Commands.CommandCoords;
import de.founntain.FounnPlugin.Commands.CommandBack;
import de.founntain.FounnPlugin.Commands.CommandStartKit;
import de.founntain.FounnPlugin.Events.OnAsyncPlayerChatEvent;
import de.founntain.FounnPlugin.Events.OnBlockBreakEvent;
import de.founntain.FounnPlugin.Events.OnEntityDamageEvent;
import de.founntain.FounnPlugin.Events.OnPlayerChangedWorldEvent;
import de.founntain.FounnPlugin.Events.OnPlayerDeathEvent;
import de.founntain.FounnPlugin.Events.OnPlayerJoinEvent;
import de.founntain.FounnPlugin.Events.OnPlayerQuitEvent;

public class FounnPlugin extends JavaPlugin{
	
	private Server Server;
	private String ConsolePrefix = "[" + ChatColor.BLUE +"FounnPlugin" + ChatColor.WHITE + "] ";
	
	public FounnPlugin() {
		this.Server = this.getServer();
		
		DeathCoord.DeathCoords = new ArrayList<DeathCoord>();
	}
	
	@Override
	public void onEnable() {		
		//Registering Events
		this.sendConsoleMessage(ChatColor.YELLOW + "registering events...");
		
		this.registerEvent(new OnPlayerDeathEvent());
		this.registerEvent(new OnPlayerJoinEvent());
		this.registerEvent(new OnPlayerQuitEvent());
		this.registerEvent(new OnEntityDamageEvent());
		this.registerEvent(new OnBlockBreakEvent());
		this.registerEvent(new OnPlayerChangedWorldEvent());
		this.registerEvent(new OnAsyncPlayerChatEvent());
		
		this.sendConsoleMessage(ChatColor.GREEN + "finished registering events...");
		
		//Registering Commands
		this.sendConsoleMessage(ChatColor.YELLOW + "registering commands");
		
		this.getCommand("startKit").setExecutor(new CommandStartKit());
		this.getCommand("coords").setExecutor(new CommandCoords());
		this.getCommand("back").setExecutor(new CommandBack());
		
		this.sendConsoleMessage(ChatColor.GREEN +  "finished registering commands");
	}
	
	@Override
	public void onDisable() {
		
	}	
	
	private void registerEvent(Listener listener) {
		this.Server.getPluginManager().registerEvents(listener, this);
	}
	
	private void sendConsoleMessage(String msg) {
		this.Server.getConsoleSender().sendMessage(this.ConsolePrefix + msg);
	}
}
