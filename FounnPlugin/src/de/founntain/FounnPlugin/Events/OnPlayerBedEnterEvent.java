package de.founntain.FounnPlugin.Events;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;

import de.founntain.FounnPlugin.Utilities.BedMap;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerBedEnterEvent implements Listener{
	private static final long TIME_MORNING = 1000;

	@EventHandler
	public void onPlayerBedEnterEvent(PlayerBedEnterEvent e) {
		if(e.getBedEnterResult() != BedEnterResult.OK) return;
		
		Player player = e.getPlayer();
		World world = player.getWorld();
		 
		BedMap.usersInBeds.put(player.getUniqueId(), true);
		
		int totalPlayers =  world.getPlayers().size();
		int playersSleeping = this.getNumberOfPlayersSleeping(player, world);
		 
		if(totalPlayers == 1 && playersSleeping == 1) {
			world.setTime(TIME_MORNING);
			 
			Bukkit.broadcastMessage("[" + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " 
					+ ChatColor.GOLD + "Guten Morgen " + player.getName());
			 
			this.disableRainIfRaining(world);
			 
			BedMap.usersInBeds.clear();
			
			return;
		}else if(playersSleeping == 0) return;
		 
		int percentValue = (int) (((double) playersSleeping / (double) totalPlayers) * 100);
		 
		if(percentValue < 33) return;
		 
		Bukkit.broadcastMessage("[" + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " 
				+ ChatColor.GOLD + "Es schlafen " + percentValue + "% der Spieler. Also steht auf ihr Spasten!");
		 
		world.setTime(TIME_MORNING);
		
		BedMap.usersInBeds.clear();
		 
		this.disableRainIfRaining(world);
	}
	 
	private int getNumberOfPlayersSleeping(Player player, World world) {
		return BedMap.usersInBeds.size();
	}
	 
	private void disableRainIfRaining(World world) {
		if(!world.isClearWeather())
			world.setClearWeatherDuration(48000);
	}
}
