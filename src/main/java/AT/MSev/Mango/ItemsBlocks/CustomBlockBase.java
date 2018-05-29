package AT.MSev.Mango.ItemsBlocks;

import AT.MSev.Mango.*;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getLogger;

public class CustomBlockBase extends CustomItemBase {
    public ArrayList<CustomBlockInstance> Logicals = new ArrayList<CustomBlockInstance>();
    public static ArrayList<CustomBlockBase> Blocks = new ArrayList<CustomBlockBase>();
    public Boolean Breakable = true;

    public CustomBlockBase(String name, Material appearance)
    {
        super(name, appearance);
        SetTag("Custom", new NBTTagString("Block"));
        Blocks.add(this);
        LoadState();
    }

    public void PlaceEvent(final BlockPlaceEvent e)
    {
        Logicals.add(new CustomBlockInstance(e.getBlockPlaced().getLocation(), e.getPlayer()));
        SaveState();
    }

    public void BreakEvent(BlockBreakEvent e, CustomBlockInstance cbi)
    {
        Block b = e.getBlock();
        World w = b.getWorld();
        Logicals.remove(cbi);
        SaveState();
        e.setCancelled(true);
        if(Breakable) {
            b.setType(Material.AIR);
            w.dropItemNaturally(b.getLocation(), CustomItemBase.Get(Name).Physical);
        }

    }

    void SaveState()
    {
        ArrayList<String> saveable = new ArrayList<String>();
        for(CustomBlockInstance cim : Logicals) saveable.add(CustomBlockInstance.format(cim));
        Main.config.getConfig().set("Block." + Name + ".Instances", saveable);
        Main.config.saveConfig();
    }

    void LoadState()
    {
        if(Main.config.getConfig().contains("Block." + Name + ".Instances"))
        {
            Logicals = new ArrayList<CustomBlockInstance>();
            ArrayList<String> loadable = (ArrayList<String>) Main.config.getConfig().get("Block." + Name + ".Instances");
            for(String s : loadable) Logicals.add(CustomBlockInstance.parse(s));
        }
    }
}
