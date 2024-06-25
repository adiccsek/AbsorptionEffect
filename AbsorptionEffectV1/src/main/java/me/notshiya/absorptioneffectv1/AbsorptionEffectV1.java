package me.notshiya.absorptioneffectv1;

import me.notshiya.absorptioneffectv1.listeners.MainAbsorbHandler;
import me.notshiya.absorptioneffectv1.listeners.EntityDamageHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbsorptionEffectV1 extends JavaPlugin {
    private static AbsorptionEffectV1 instance;
    private ItemStack goldenNuggetWithDisplayName;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EntityDamageHandler(), this);
        getServer().getPluginManager().registerEvents(new MainAbsorbHandler(), this);
        instance = this;

        goldenNuggetWithDisplayName = SetDisplayName.createGoldNugget();
    }

    public static AbsorptionEffectV1 getInstance() {
        return instance;
    }
    public ItemStack getGoldenNuggetWithDisplayName() {
        return goldenNuggetWithDisplayName;
    }
}
