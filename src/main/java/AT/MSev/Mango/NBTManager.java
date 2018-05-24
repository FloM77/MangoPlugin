package AT.MSev.Mango;

import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class NBTManager {
    public static ItemStack AddItemNBT(ItemStack item, String key, NBTBase nbt)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbtTags = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        nbtTags.set(key, nbt);
        nmsCopy.setTag(nbtTags);
        return CraftItemStack.asBukkitCopy(nmsCopy);
    }

    public static ItemStack RemoveItemNBT(ItemStack item, String key)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbtTags = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        nbtTags.remove(key);
        nmsCopy.setTag(nbtTags);
        return CraftItemStack.asBukkitCopy(nmsCopy);
    }

    public static Boolean HasKey(ItemStack item, String key)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);

        NBTTagCompound nbtTags = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        return nbtTags.hasKey(key);
    }

    public static Boolean HasTag(ItemStack item)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        return nmsCopy.hasTag();
    }

    public static String GetString(ItemStack item, String key)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbtTags = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        return nbtTags.getString(key);
    }
}
