package AT.MSev.Mango.Zones;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ZoneBlockProtect extends ZoneBase {

    public ZoneBlockProtect(Location bound1, Location bound2, @Nullable String owner, @Nullable ArrayList<String> bypass)
    {
        super(bound1, bound2, owner, bypass);
        ZoneType = "ZoneBlockProtect";

        SaveStates(null);
    }

    @Override
    public void BreakInZoneEvent(BlockBreakEvent e) {
        String id = e.getPlayer().getUniqueId().toString();
        if(!Bypass.contains(id)) {
            if (Setting.IsIn(e.getBlock().getLocation())) {
                e.setCancelled(true);
            }
        }
    }
}
