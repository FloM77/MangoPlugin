package AT.MSev.Mango.Zones;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ZoneState implements ConfigurationSerializable
{
    Location Bound1, Bound2;
    String Owner;
    ArrayList<String> Bypass;
    String ZoneType;

    public ZoneState(Location b1, Location b2, String owner, ArrayList<String> bypass, String zoneType)
    {
        Bound1 = b1;
        Bound2 = b2;
        Owner = owner;
        Bypass = bypass;
        ZoneType = zoneType;
    }

    public Map<String, Object> serialize() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("b1", Bound1);
        result.put("b2", Bound2);
        result.put("Owner", Owner);
        result.put("Bypass", Bypass);
        result.put("ZoneType", ZoneType);
        return result;
    }

    public static ZoneState deserialize(Map<String, Object> args) {
        Location b1 = ((Location)args.get("b1"));
        Location b2 = ((Location)args.get("b2"));
        String owner = ((String)args.get("Owner"));
        ArrayList<String> bypass = ((ArrayList<String>)args.get("Bypass"));
        String type = ((String)args.get("ZoneType"));
        ZoneState ret = new ZoneState(b1,b2,owner,bypass, type);
        return ret;
    }

}