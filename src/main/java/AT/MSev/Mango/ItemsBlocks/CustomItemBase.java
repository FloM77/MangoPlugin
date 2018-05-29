package AT.MSev.Mango.ItemsBlocks;

import AT.MSev.Mango.*;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagInt;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class CustomItemBase {
    public String Name;
    public ItemStack Physical;
    public static ArrayList<CustomItemBase> All = new ArrayList<CustomItemBase>();
    Random random = new Random();
    public CustomItemBase(String name, Material appearance)
    {
        Name = name;
        Physical = new ItemStack(appearance);
        SetTag("Custom", new NBTTagString("Base"));
        SetTag("Name", new NBTTagString(Name));
        All.add(this);
        Rename(Name);
    }

    public void Rename(String newName)
    {
        MangoUtils.ItemRename(Physical, newName);
    }

    public void SetLore(ArrayList<String> newLore)
    {
        MangoUtils.ItemRelore(Physical, newLore);
    }

    public void SetTag(String key, NBTBase nbt)
    {
        Physical = NBTManager.AddItemNBT(Physical, key, nbt);
    }

    public void RemoveTag(String key)
    {
        Physical = NBTManager.RemoveItemNBT(Physical, key);
    }

    public void Give(Player p, Boolean stackable)
    {
        if(!stackable){
        int randomID = random.nextInt(Integer.MAX_VALUE);
        SetTag("Unstackable", new NBTTagInt(randomID));
        }
        p.getInventory().addItem(Physical);
    }

    public static CustomItemBase Get(String exactName)
    {
        for(CustomItemBase eb: CustomItemBase.All)
        {
            if(eb.Name.equalsIgnoreCase(exactName))
            {
                return eb;
            }
        }
        return null;
    }

    public static boolean CompareWithItem(CustomItemBase ebase, ItemStack item)
    {
        return true;
    }
}
