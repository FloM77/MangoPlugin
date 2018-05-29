package AT.MSev.Mango.Zones;

import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;

public class ZoneBlockProtect extends ZoneBase {
    public ZoneBlockProtect(Location bound1, Location bound2)
    {
        super(bound1, bound2);
    }

    @Override
    public void BreakInZone(BlockBreakEvent e) {
        if(Setting.IsIn(e.getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }
}
