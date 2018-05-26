package AT.MSev.Mango;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class CustomBlockInstance {
    Location Logical;
    Player Owner;

    public CustomBlockInstance(Location location, Player player)
    {
        Logical = location;
        getLogger().info(Logical.getWorld().toString());
        Owner = player;
    }

    static public CustomBlockInstance parse(String from)
    {
        String[] vars = from.split(";");
        final World world = Bukkit.getWorld(vars[0]);
        final Double x = Double.parseDouble(vars[1]);
        final Double y = Double.parseDouble(vars[2]);
        final Double z = Double.parseDouble(vars[3]);
        final Player owner = Bukkit.getPlayer(UUID.fromString(vars[4]));
        return new CustomBlockInstance(new Location(world, x, y, z), owner);
    }

    static public String format(CustomBlockInstance from)
    {
        String world = from.Logical.getWorld().getName();
        String x = String.format("%.0f", from.Logical.getX());
        String y = String.format("%.0f", from.Logical.getY());
        String z = String.format("%.0f", from.Logical.getZ());
        String owner = from.Owner.getUniqueId().toString();
        return world + ";" + x + ";" + y + ";" + z + ";" + owner;
    }
}
