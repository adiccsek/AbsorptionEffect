package me.notshiya.absorptioneffectv1.listeners;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class MainAbsorbHandler implements Listener {
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the action is a right-click
        if (event.getAction().toString().contains("RIGHT_CLICK")) {
            // Check if the player is holding a gold nugget in either hand
            if (player.getInventory().getItemInMainHand().getType() == Material.GOLD_NUGGET
                    || player.getInventory().getItemInOffHand().getType() == Material.GOLD_NUGGET) {

                UUID playerUUID = player.getUniqueId();
                long currentTime = System.currentTimeMillis();

                // Check for cooldown
                if (cooldowns.containsKey(playerUUID)) {
                    long timeSinceLastUse = currentTime - cooldowns.get(playerUUID);
                    if (timeSinceLastUse < 5000) {
                        player.sendMessage(ChatColor.YELLOW + "sEffect | " + ChatColor.RED + "You must wait " + (5 - (timeSinceLastUse / 1000)) + " seconds before using this effect again!");
                        event.setCancelled(true);
                        return;
                    }
                }

                boolean success = false;
                double currentAbsorption = player.getAbsorptionAmount();
                double maxAbsorption = 18;
                // Check if the player has reached the maximum absorption amount
                if (currentAbsorption + 4 > maxAbsorption) {
                    player.sendMessage(ChatColor.YELLOW + "sEffect | " + ChatColor.RED + "You have reached the maximum absorption amount!");
                    event.setCancelled(true);
                    return;
                }
                // Check if the player has absorption
                if (currentAbsorption == 0) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 999999, 0, false, false));
                } else { // Add 4 absorption hearts
                    player.setAbsorptionAmount(Math.min(currentAbsorption + 4, maxAbsorption));
                }
                success = true;
                //Send successful message
                player.sendMessage(ChatColor.YELLOW + "sEffect | " + ChatColor.GREEN + "Absorption effect added!");
                System.out.println("Absorption effect added to " + player.getName());

                // Set new cooldown
                cooldowns.put(playerUUID, currentTime);

                // Remove one gold nugget from the player's inventory
                ItemStack itemMainHand = player.getInventory().getItemInMainHand();
                ItemStack itemOffHand = player.getInventory().getItemInOffHand();

                if (itemMainHand.getType() == Material.GOLD_NUGGET) {
                    itemMainHand.setAmount(itemMainHand.getAmount() - 1);
                } else if (itemOffHand.getType() == Material.GOLD_NUGGET) {
                    itemOffHand.setAmount(itemOffHand.getAmount() - 1);
                }
                FireworkSummonHandler.launchYellowFirework(player, success);

            }
        }
    }
}
