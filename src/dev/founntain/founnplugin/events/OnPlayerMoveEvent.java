package dev.founntain.founnplugin.events;

import dev.founntain.founnplugin.classes.Utilities;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerMoveEvent implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e){
        Player player = e.getPlayer();

        if(player == null) return;

        if(!this.checkIfItemInHandIsWand(player)) return;

        ItemStack blockToPlace = player.getInventory().getItemInMainHand();

        Location locationToPlace = player.getLocation();
        int x = (int) locationToPlace.getX();
        int y = (int) locationToPlace.getY();
        int z = (int) locationToPlace.getZ();

        World world = player.getWorld();

        int size = 0;

        for(int xc = x - size; xc <= (x + size); xc++){
            for(int zc = z - size; zc <= (z + size); zc++){
                Utilities.spawnRandomFirework(new Location(world, xc, y, zc));
            }
        }
    }

    private boolean checkIfItemInHandIsWand(final Player player){
        return player.getInventory().getItemInOffHand().getType() == Material.DIAMOND;
    }
}
