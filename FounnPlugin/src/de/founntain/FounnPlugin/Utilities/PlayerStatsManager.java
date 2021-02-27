package de.founntain.FounnPlugin.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import com.google.gson.Gson;

import de.founntain.FounnPlugin.PlayerStat;

public class PlayerStatsManager {
	private final String FOLDER_PATH = "plugins/FounnPlugin/stats/";
	
	public PlayerStatsManager() {	
		File file = new File("plugins/FounnPlugin/Stats");
		
		if(!file.exists())
			file.mkdirs();
	}
	
	public PlayerStat getStatsOfPlayer(UUID playerId) {
		try {
			String path = this.FOLDER_PATH + playerId + ".json";
			File file = new File(path);
			
			if(!file.exists()) {
				PlayerStat newPlayerStat = new PlayerStat(playerId);
				
				this.saveStatsOfPlayer(newPlayerStat);
				
				return newPlayerStat;
			}
			
			Gson gson = new Gson();
			String data = "";
			
			try(Scanner scanner = new Scanner(file)){
				while(scanner.hasNextLine()) 
					data = scanner.nextLine();
			}
			
			return gson.fromJson(data, PlayerStat.class);
		}catch(IOException ex) {
			return null;
		}
	} 
	
	public void saveStatsOfPlayer(PlayerStat playerStat) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(playerStat);
			String path = this.FOLDER_PATH + playerStat.getPlayerId() + ".json";
			
			File statFile = new File(path);
			
			statFile.createNewFile();
			
			statFile.setWritable(true);
			statFile.setReadable(true);
			
			try(FileWriter fw = new FileWriter(path)){
				fw.write(json);
			}
		}catch(IOException ex) {
			return;
		}
	}
}
