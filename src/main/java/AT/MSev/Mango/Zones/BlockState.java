package AT.MSev.Mango.Zones;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockState implements ConfigurationSerializable
{
    public Location RelLoc;
    public String BlockType;

    public BlockState(Location l, String m)
    {
        RelLoc = l;
        BlockType = m;
    }

    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("relloc", RelLoc);
        result.put("type", BlockType);
        return result;
    }

    public static BlockState deserialize(Map<String, Object> args) {
        Location relloc = ((Location)args.get("relloc"));
        String type = ((String)args.get("type"));
        BlockState ret = new BlockState(relloc, type);
        return ret;
    }
}
