package de.founntain.FounnPlugin;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import de.founntain.FounnPlugin.Commands.CoordCommand;
import de.founntain.FounnPlugin.Commands.SendItemCommand;
import de.founntain.FounnPlugin.Commands.SpawnCustomMobCommand;
import de.founntain.FounnPlugin.Commands.MenuCommand;
import de.founntain.FounnPlugin.Commands.BackCommand;
import de.founntain.FounnPlugin.Commands.StartKitCommand;

import de.founntain.FounnPlugin.Events.OnAsyncPlayerChatEvent;
import de.founntain.FounnPlugin.Events.OnBlockBreakEvent;
import de.founntain.FounnPlugin.Events.OnEnitityDamageByEnityEvent;
import de.founntain.FounnPlugin.Events.OnEntityDamageEvent;
import de.founntain.FounnPlugin.Events.OnEntityDeathEvent;
import de.founntain.FounnPlugin.Events.OnInventoryClickEvent;
import de.founntain.FounnPlugin.Events.OnInventoryCloseEvent;
import de.founntain.FounnPlugin.Events.OnPlayerChangedWorldEvent;
import de.founntain.FounnPlugin.Events.OnPlayerDeathEvent;
import de.founntain.FounnPlugin.Events.OnPlayerJoinEvent;
import de.founntain.FounnPlugin.Events.OnPlayerQuitEvent;
import de.founntain.FounnPlugin.Recipes.ConcreteRecipe;
import de.founntain.FounnPlugin.Recipes.GrassBlockRecipe;
import de.founntain.FounnPlugin.Recipes.WhiteDyeRecipe;

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
		this.registerEvent(new OnPlayerChangedWorldEvent());
		this.registerEvent(new OnEntityDamageEvent());
		this.registerEvent(new OnEnitityDamageByEnityEvent());
		this.registerEvent(new OnEntityDeathEvent(this));
		this.registerEvent(new OnBlockBreakEvent());
		this.registerEvent(new OnAsyncPlayerChatEvent());
		this.registerEvent(new OnInventoryClickEvent());
		this.registerEvent(new OnInventoryCloseEvent());
		
		this.sendConsoleMessage(ChatColor.GREEN + "finished registering events...");
		
		//Registering Commands
		this.sendConsoleMessage(ChatColor.YELLOW + "registering commands");
		
		this.registerCommand("startKit", new StartKitCommand());
		this.registerCommand("coords", new CoordCommand());
		this.registerCommand("back", new BackCommand());
		this.registerCommand("sendItem", new SendItemCommand());
		this.registerCommand("menu", new MenuCommand());
		this.registerCommand("spawnCustomMob", new SpawnCustomMobCommand(this));
		
		this.sendConsoleMessage(ChatColor.GREEN +  "finished registering commands");
		
		//Registering Recipes
		this.sendConsoleMessage(ChatColor.YELLOW + "registering recipes");
		
		new WhiteDyeRecipe(this);
		new ConcreteRecipe(this);
		new GrassBlockRecipe(this);
		
		this.sendConsoleMessage(ChatColor.GREEN +  "finished registering recipes");
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
