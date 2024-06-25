package me.notshiya.absorptioneffectv1.listeners;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageHandler implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // Check the cause of the damage
        if (event.getDamager() instanceof Firework) {
            Firework firework = (Firework) event.getDamager();
            if (firework.hasMetadata("nodamage")) {
                //Set the event to cancelled, if the damage was caused by a firework
                event.setCancelled(true);
            }

        }
    }
}
