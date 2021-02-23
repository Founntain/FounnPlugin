package de.founntain.FounnPlugin;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import de.founntain.FounnPlugin.Commands.CoordCommand;
import de.founntain.FounnPlugin.Commands.DayCommand;
import de.founntain.FounnPlugin.Commands.DeathBoxCommand;
import de.founntain.FounnPlugin.Commands.SendItemCommand;
import de.founntain.FounnPlugin.Commands.SpawnCommand;
import de.founntain.FounnPlugin.Commands.SpawnCustomMobCommand;
import de.founntain.FounnPlugin.Commands.MenuCommand;
import de.founntain.FounnPlugin.Commands.BackCommand;
import de.founntain.FounnPlugin.Commands.ClearInventoryCommand;
import de.founntain.FounnPlugin.Commands.StartKitCommand;
import de.founntain.FounnPlugin.Commands.TestCommand;
import de.founntain.FounnPlugin.Commands.WeatherCommand;
import de.founntain.FounnPlugin.Events.OnAsyncPlayerChatEvent;
import de.founntain.FounnPlugin.Events.OnBlockBreakEvent;
import de.founntain.FounnPlugin.Events.OnCraftItemEvent;
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
import de.founntain.FounnPlugin.Recipes.DeathBoxRecipe;
import de.founntain.FounnPlugin.Recipes.EnhancedEnchantmentsRecipes;
import de.founntain.FounnPlugin.Recipes.GrassBlockRecipe;
import de.founntain.FounnPlugin.Recipes.SlapsToMaterialRecipe;
import de.founntain.FounnPlugin.Recipes.StairsToMaterialRecipe;
import de.founntain.FounnPlugin.Recipes.WhiteDyeRecipe;

public class FounnPlugin extends JavaPlugin{
	
	private Server server;
	private String consolePrefix = "[" + ChatColor.BLUE +"FounnPlugin" + ChatColor.WHITE + "] ";
	public static UUID founntainUUID = UUID.fromString("1f146f64-96fb-400c-971a-8d68e7d96b69");
	
	public FounnPlugin() {
		this.server = this.getServer();
		
		DeathCoord.deathCoords = new HashMap<UUID, DeathCoord>();
		DeathItems.items = new HashMap<UUID, ItemStack[]>();
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
		this.registerEvent(new OnCraftItemEvent());
		
		this.sendConsoleMessage(ChatColor.GREEN + "finished registering events...");
		
		//Registering Commands
		this.sendConsoleMessage(ChatColor.YELLOW + "registering commands");
		
		this.registerCommand("startKit", new StartKitCommand());
		this.registerCommand("coords", new CoordCommand());
		this.registerCommand("back", new BackCommand());
		this.registerCommand("sendItem", new SendItemCommand());
		this.registerCommand("menu", new MenuCommand());
		this.registerCommand("spawnCustomMob", new SpawnCustomMobCommand(this));
		this.registerCommand("deathBox", new DeathBoxCommand());
		this.registerCommand("clearInv", new ClearInventoryCommand());
		this.registerCommand("day", new DayCommand());
		this.registerCommand("spawn", new SpawnCommand());
		this.registerCommand("test", new TestCommand());
		this.registerCommand("w", new WeatherCommand());
		
		this.sendConsoleMessage(ChatColor.GREEN +  "finished registering commands");
		
		//Registering Recipes
		this.sendConsoleMessage(ChatColor.YELLOW + "registering recipes");
		
		new WhiteDyeRecipe(this);
		new ConcreteRecipe(this);
		new GrassBlockRecipe(this);
		new DeathBoxRecipe(this);
		new StairsToMaterialRecipe(this);
		new SlapsToMaterialRecipe(this);
		new EnhancedEnchantmentsRecipes(this);
		
		this.sendConsoleMessage(ChatColor.GREEN +  "finished registering recipes");
	}
	
	@Override
	public void onDisable() {
		
	}	
	
	private void registerEvent(Listener listener) {
		this.server.getPluginManager().registerEvents(listener, this);
	}
	
	private void sendConsoleMessage(String msg) {
		this.server.getConsoleSender().sendMessage(this.consolePrefix + msg);
	}
	
	private void registerCommand(String command, CommandExecutor commandExecutor) {
		this.getCommand(command).setExecutor(commandExecutor);
	}
}
