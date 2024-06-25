package me.notshiya.absorptioneffectv1;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetDisplayName {

    public static ItemStack createGoldNugget() {
       ItemStack goldNugget = new ItemStack(Material.GOLD_NUGGET);
       ItemMeta goldNuggetMeta = goldNugget.getItemMeta();

       if (goldNuggetMeta != null) {
           goldNuggetMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Absorption Effect I");
           goldNugget.setItemMeta(goldNuggetMeta);
       }
       return goldNugget;
   }
}
