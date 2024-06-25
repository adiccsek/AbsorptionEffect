package me.notshiya.absorptioneffectv1.listeners;

import me.notshiya.absorptioneffectv1.AbsorptionEffectV1;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
public class FireworkSummonHandler{

    public static void launchYellowFirework(Player player, boolean success) {
        if (success) {
            // Get the player's location and adjust the Y coordinate to be slightly above the player
            Location fireworkLocation = player.getLocation().add(0, 1, 0);

            // Firework effect with yellow color, ball shape, and flicker
            Firework firework = player.getWorld().spawn(fireworkLocation, Firework.class);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.setPower(0);
            // Power adjusted to ensure a smaller burst
            // Add metadata to the firework to prevent damage
            firework.setMetadata("nodamage", new FixedMetadataValue(AbsorptionEffectV1.getInstance(), true));

            fireworkMeta.addEffect(FireworkEffect.builder()
                    .withColor(Color.YELLOW)
                    .with(FireworkEffect.Type.BALL)
                    .withFlicker()
                    .build());
            // Apply the firework meta to the firework
            firework.setFireworkMeta(fireworkMeta);

            // Schedule the firework to explode immediately
            Bukkit.getScheduler().runTaskLater(AbsorptionEffectV1.getInstance(), firework::detonate, 1L);
        }
    }
}
