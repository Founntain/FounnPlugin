package dev.founntain.founnplugin.events;

import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

public class OnEntityExplodeEvent implements Listener {
    private final Plugin plugin;

    public OnEntityExplodeEvent(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent e){
        if(e.getEntity() instanceof Creeper) {
            this.checkForCreeperExplosion(e);
            return;
        }
    }

    private void checkForCreeperExplosion(EntityExplodeEvent e){
        e.getEntity().getWorld().playSound(e.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);

        e.setCancelled(true);
    }
}
