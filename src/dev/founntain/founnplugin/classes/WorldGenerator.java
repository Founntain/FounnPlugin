package dev.founntain.founnplugin.classes;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.*;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.bukkit.World.Environment;

public class WorldGenerator {
	private String worldname;
	private Player player;
	private static final String FOLDER_PATH = "plugins/FounnPlugin/";
	
	public WorldGenerator(String worldname, Player player) {
		this.worldname = worldname;
		this.player = player;
	}
	
	public static ArrayList<String> getWorldnamesFromJson() {
		Gson gson = new Gson();
		
		File file = new File(WorldGenerator.FOLDER_PATH + "worlds.json");
		
		if(file.exists()) {
			try(Scanner scan = new Scanner(file)){
				while(scan.hasNext()) {
					String data = scan.next();
					
					return gson.fromJson(data, new TypeToken<ArrayList<String>>(){}.getType());
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
		return new ArrayList<String>();
	}
	
	private void saveWorldsConfig(boolean removeWorld) {
		Gson gson = new Gson();
		
		File file = new File(WorldGenerator.FOLDER_PATH + "worlds.json");
		ArrayList<String> worlds = WorldGenerator.getWorldnamesFromJson();
		
		if(removeWorld)
			worlds.remove(this.worldname);
		else
			worlds.add(this.worldname);
		
		try(FileWriter fw = new FileWriter(file)){
			fw.write(gson.toJson(worlds));
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}
	private void delete(String file) {
		try {
			Files.walk(Paths.get(file))
			    .map(Path::toFile)
			    .sorted((o1, o2) -> -o1.compareTo(o2))
			    .forEach(File::delete);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void deleteWorld() {
		World worldToUnload = Bukkit.getWorld(this.worldname);
		
		if(worldToUnload != null)
			Bukkit.getServer().unloadWorld(worldToUnload, false);
		
		this.delete(this.worldname);
		
		this.saveWorldsConfig(true);
	}
	
	public World generateWorld() {
		WorldCreator wc = new WorldCreator(this.worldname);
		
		wc.environment(Environment.NORMAL);
		wc.type(WorldType.NORMAL);

		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.sendMessage(ChatColor.GRAY + this.worldname + " | " + wc.environment() + " | " +  wc.type() + " | "+ wc.seed() + " | welt wird erstellt mit lags rechnen");
		}

		return wc.createWorld();
	}
	
	public World generateWorld(final Environment env, final WorldType type) {
		WorldCreator wc = new WorldCreator(this.worldname);
		
		wc.environment(env);
		wc.type(type);
		
		try {
			this.saveWorldsConfig(false);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}

		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			p.sendMessage(ChatColor.GRAY + this.worldname + " | " + wc.environment() + " | " +  wc.type() + " | "+ wc.seed() + " | welt wird erstellt mit lags rechnen");
		}
		
		return wc.createWorld();
		
		
	}
}
