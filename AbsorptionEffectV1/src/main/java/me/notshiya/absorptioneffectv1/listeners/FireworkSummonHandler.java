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
            Location fireworkLocation = player.getLocation().add(0, 1, 0);

            Firework firework = player.getWorld().spawn(fireworkLocation, Firework.class);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.setPower(0);

            firework.setMetadata("nodamage", new FixedMetadataValue(AbsorptionEffectV1.getInstance(), true));

            fireworkMeta.addEffect(FireworkEffect.builder()
                    .withColor(Color.YELLOW)
                    .with(FireworkEffect.Type.BALL)
                    .withFlicker()
                    .build());

            firework.setFireworkMeta(fireworkMeta);
            Bukkit.getScheduler().runTaskLater(AbsorptionEffectV1.getInstance(), firework::detonate, 1L);
        }
    }
}
