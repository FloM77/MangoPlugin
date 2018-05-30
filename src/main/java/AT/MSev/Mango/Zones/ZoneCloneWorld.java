package AT.MSev.Mango.Zones;

import AT.MSev.Mango.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ZoneCloneWorld extends ZoneBase {
    public ZoneCloneWorld(Location bound1, Location bound2, @Nullable String owner, @Nullable ArrayList<String> bypass)
    {
        super(bound1, bound2, owner, bypass);
        ZoneType = "ZoneCloneWorld";

        SaveStates(null);
    }
    public FileConfiguration Config = null;
    @Override
    public void ZoneCommandEvent(Player source, String[] strings) {
        if(strings.length>1)
        {
            if(strings[0].equalsIgnoreCase("save"))
            {
                Save(strings[1]);
            }
            else if (strings[0].equalsIgnoreCase("load"))
            {
                Load(strings[1]);
            }
        }
    }

    public void Save(String filename)
    {
        ArrayList<BlockState> blocks = new ArrayList<BlockState>();
        for(int i1=0;i1<Setting.ZoneXSize();i1++)
        {
            for(int i2=0;i2<Setting.ZoneYSize();i2++)
            {
                for(int i3=0;i3<Setting.ZoneZSize();i3++)
                {
                    Location itLoc = Setting.RelLocLesser(i1, i2, i3);
                    Material type = itLoc.getWorld().getBlockAt(itLoc).getType();
                    Location relloc = new Location(Setting.getWorld(), i1, i2, i3);
                    blocks.add(new BlockState(relloc, type.toString()));
                }
            }
        }
        Config = new YamlConfiguration();
        Config.set("Blocks", blocks);
        try {
            Config.save(new File(Main.Folder, filename + ".mango"));
        }
        catch(Exception ex) {
            ex.printStackTrace(); }
    }

    public void Load(String filename)
    {
        Config = YamlConfiguration.loadConfiguration(new File(Main.Folder, filename + ".mango"));
        ArrayList<BlockState> blocks = (ArrayList<BlockState>)Config.get("Blocks");
        for(BlockState bs : blocks)
        {
            Location realLoc = Setting.RelLocLesser(bs.RelLoc.getX(), bs.RelLoc.getY(), bs.RelLoc.getZ());
            Setting.getWorld().getBlockAt(realLoc).setType(Material.getMaterial(bs.BlockType));
        }
    }
}

