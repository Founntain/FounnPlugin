package de.founntain.founnplugin;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.World.Environment;

public class WorldGenerator {
	private String worldname;
	private Player player;
	
	public WorldGenerator(String worldname, Player player) {
		this.worldname = worldname;
		this.player = player;
	}
	
	public World generateWorld() {
		WorldCreator wc = new WorldCreator(this.worldname);
		
		wc.environment(Environment.NORMAL);
		wc.type(WorldType.NORMAL);
		
		this.player.sendMessage(ChatColor.GRAY + this.worldname + " | " + wc.environment() + " | " +  wc.type() + " | "+ wc.seed());
		
		return wc.createWorld();
	}
	
	public World generateWorld(final Environment env, final WorldType type) {
		WorldCreator wc = new WorldCreator(this.worldname);
		
		wc.environment(env);
		wc.type(type);
		
		this.player.sendMessage(ChatColor.GRAY + this.worldname + " | " + wc.environment() + " | " +  wc.type() + " | "+ wc.seed());
		
		return wc.createWorld();
	}
}
