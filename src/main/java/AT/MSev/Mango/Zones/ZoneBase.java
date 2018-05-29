package AT.MSev.Mango.Zones;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;

public class ZoneBase {

    public ZoneSetting Setting;
    public static ArrayList<ZoneBase> All = new ArrayList<ZoneBase>();

    public ZoneBase(Location bound1, Location bound2)
    {
        Setting = new ZoneSetting(bound1, bound2);
        All.add(this);
    }

    public void BreakInZone(BlockBreakEvent e) {}
}
