package AT.MSev.Mango.Commands;

import AT.MSev.Mango.Zones.ZoneBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandZone implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Boolean inAtleastOne = false;
        Player player = ((Player)commandSender);

        if(command.getName().equals("zone"))
        {
            for(ZoneBase zb : ZoneBase.All)
            {
                if(zb.Setting.IsIn(player.getLocation()))
                {
                    zb.ZoneCommandEvent(player, strings);
                    inAtleastOne = true;
                }
            }
        }

        return inAtleastOne;
    }
}
