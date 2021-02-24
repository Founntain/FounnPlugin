package de.founntain.FounnPlugin.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

import net.md_5.bungee.api.ChatColor;

public class OnPlayerBedEnterEvent implements Listener{
	private static final long TIME_MORNING = 1000;
	
	 @EventHandler
	 public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
		 if(e.getBedEnterResult() != BedEnterResult.OK) return;
		 
		 System.out.println("BedEvent");
		 
		 Player player = e.getPlayer();
		 World world = player.getWorld();
		 
		 int totalPlayers =  world.getPlayers().size();
		 int playersSleeping = this.getNumberOfPlayersSleeping(player, world);
		 
		 if(playersSleeping == 1) {
			 world.setTime(TIME_MORNING);
			 
			 Bukkit.broadcastMessage("[" + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " 
					 + ChatColor.GOLD + "Guten Morgen " + player.getName());
			 
			 this.disableRainIfRaining(world);
			 
			 return;
		 }else if(playersSleeping == 0) return;
		 
		 int percentValue = (int) ((playersSleeping / totalPlayers) * 100);
		 
		 if(percentValue < 33) return;
		 
		 Bukkit.broadcastMessage("[" + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " 
				 + ChatColor.GOLD + "es schlafen " + percentValue + "% der Spieler. Also steht auf ihr Spasten!");
		 
		 world.setTime(TIME_MORNING);
		 
		 this.disableRainIfRaining(world);
	 }
	 
	 private int getNumberOfPlayersSleeping(Player player, World world) {
		 ArrayList<Player> players = (ArrayList<Player>) world.getPlayers();
		 
		 if(players.size() == 1)
			 return 1;
		 
		 int playersSleeping = 0;
		 
		 for(Player p : players) {
			if(!p.isSleeping()) continue;
			
			if(!p.getWorld().getUID().equals(world.getUID())) continue;
			
			playersSleeping++;
		 }
		 
		 return playersSleeping;
	 }
	 
	 private void disableRainIfRaining(World world) {
		 if(!world.isClearWeather());
		 
		 world.setClearWeatherDuration(48000);
	 }
}
