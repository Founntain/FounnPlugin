package de.founntain.FounnPlugin;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.founntain.FounnPlugin.Commands.CommandCoords;
import de.founntain.FounnPlugin.Commands.CommandSendItem;
import de.founntain.FounnPlugin.Commands.CommandMenu;
import de.founntain.FounnPlugin.Commands.CommandBack;
import de.founntain.FounnPlugin.Commands.CommandStartKit;

import de.founntain.FounnPlugin.Events.OnAsyncPlayerChatEvent;
import de.founntain.FounnPlugin.Events.OnBlockBreakEvent;
import de.founntain.FounnPlugin.Events.OnEntityDamageEvent;
import de.founntain.FounnPlugin.Events.OnInventoryClickEvent;
import de.founntain.FounnPlugin.Events.OnInventoryCloseEvent;
import de.founntain.FounnPlugin.Events.OnPlayerChangedWorldEvent;
import de.founntain.FounnPlugin.Events.OnPlayerDeathEvent;
import de.founntain.FounnPlugin.Events.OnPlayerJoinEvent;
import de.founntain.FounnPlugin.Events.OnPlayerQuitEvent;

public class FounnPlugin extends JavaPlugin{
	
	private Server Server;
	private String ConsolePrefix = "[" + ChatColor.BLUE +"FounnPlugin" + ChatColor.WHITE + "] ";
	public static UUID FounntainUUID = UUID.fromString("1f146f64-96fb-400c-971a-8d68e7d96b69");
	
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
		this.registerEvent(new OnInventoryClickEvent());
		this.registerEvent(new OnInventoryCloseEvent());
		
		this.sendConsoleMessage(ChatColor.GREEN + "finished registering events...");
		
		//Registering Commands
		this.sendConsoleMessage(ChatColor.YELLOW + "registering commands");
		
		this.registerCommand("startKit", new CommandStartKit());
		this.registerCommand("coords", new CommandCoords());
		this.registerCommand("back", new CommandBack());
		this.registerCommand("sendItem", new CommandSendItem());
		this.registerCommand("menu", new CommandMenu());
		
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
	
	private void registerCommand(String command, CommandExecutor commandExecutor) {
		this.getCommand(command).setExecutor(commandExecutor);
	}
}
