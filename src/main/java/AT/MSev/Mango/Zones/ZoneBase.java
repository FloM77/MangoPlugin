package AT.MSev.Mango.Zones;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ZoneBase implements Listener {
    ZoneSetting Setting;

    public ZoneBase(Location bound1, Location bound2)
    {
        Setting = new ZoneSetting(bound1, bound2);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    void BreakInZone(BlockBreakEvent e) {}
}
