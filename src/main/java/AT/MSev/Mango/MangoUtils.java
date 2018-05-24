package AT.MSev.Mango;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MangoUtils {
    public static void ItemRename(ItemStack item, String newName)
    {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(newName);
        item.setItemMeta(meta);
    }

    public static void ItemRelore(ItemStack item, ArrayList<String> newLore)
    {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(newLore);
        item.setItemMeta(meta);
    }
}
