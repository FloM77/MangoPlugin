package AT.MSev.Mango.Commands;

import AT.MSev.Mango.Zones.ZoneBlockProtect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandCreateZoneProtect implements CommandExecutor {
    HashMap<String, Location> Stored = new HashMap<String, Location>();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = ((Player)commandSender);
        Location PBL = player.getLocation().getBlock().getLocation();
        String playerID = player.getUniqueId().toString();

        if(command.getName().equals("startProtect"))
        {
            if(Stored.containsKey(playerID)) {
                Stored.remove(playerID);
            }

            Stored.put(playerID, PBL);
            player.sendMessage("Started Zone at " + PBL);
        }
        else if(command.getName().equals("endProtect"))
        {
            if(Stored.containsKey(playerID))
            {
                Location bound1 = Stored.get(playerID);
                Location bound2 = PBL;
                new ZoneBlockProtect(bound1, bound2);

                player.sendMessage("Ended Zone at " + PBL);
            }
        }
        return true;
    }
}
