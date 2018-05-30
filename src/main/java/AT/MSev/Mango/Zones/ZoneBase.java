package AT.MSev.Mango.Zones;

import AT.MSev.Mango.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

public class ZoneBase {
    public String ZoneType = "ZoneBase";
    Boolean toDelete = false;
    ArrayList<String> Bypass = new ArrayList<String>();
    String Owner;
    public ZoneSetting Setting;
    public static ArrayList<ZoneBase> All = new ArrayList<ZoneBase>();

    public ZoneBase(Location bound1, Location bound2, @Nullable String owner, @Nullable ArrayList<String> bypass)
    {
        Setting = new ZoneSetting(bound1, bound2);
        if(bypass!=null) {Bypass = bypass;}
        Owner = owner;
        if(Owner!=null) AddBypass(Owner);
        All.add(this);
    }

    public void BreakInZoneEvent(BlockBreakEvent e) {}

    public void ZoneCommandEvent(Player source, String[] strings) {
        String id = source.getUniqueId().toString();
        if(source.getUniqueId().toString().equals(Owner)) {

            if (strings.length > 0)
            {

                if(strings[0].equalsIgnoreCase("delete"))
                {
                    Remove();
                    source.sendMessage("Deleted");
                }

            }
            else if (strings.length > 1) {

                if (strings[0].equalsIgnoreCase("add")) {
                    Player p = Bukkit.getServer().getPlayer(strings[1]);
                    AddBypass(id);
                    p.sendMessage("Added " + strings[1]);
                }
                else if (strings[0].equalsIgnoreCase("remove")) {
                    Player p = Bukkit.getServer().getPlayer(strings[1]);
                    if(p.getUniqueId().toString().equals(Owner))
                    {
                        p.sendMessage("Can't remove owner");
                        return;
                    }
                    RemoveBypass(id);
                    p.sendMessage("Removed " + strings[1]);
                }

            } else {
                source.sendMessage("/zone add [player]");
                source.sendMessage("/zone remove [player]");
                source.sendMessage("/zone delete");
            }

            SaveStates(null);
        }
        else
        {
            source.sendMessage("You don't own this zone.");
        }
    }

    public void Remove()
    {
        Setting.Quad1 = new Location(Setting.Quad1.getWorld() , 0, 0, 0);
        Setting.Quad2 = new Location(Setting.Quad2.getWorld() , 0, 0, 0);
        toDelete = true;

        SaveStates(null);
    }

    public void AddBypass(String player)
    {
        if(!Bypass.contains(player))
            Bypass.add(player);
    }

    public void RemoveBypass(String player)
    {
        if(Bypass.contains(player))
            Bypass.remove(player);
    }

    ZoneState getState() {
        return new ZoneState(Setting.Quad1, Setting.Quad2, Owner, Bypass, ZoneType);
    }

    public static void SaveStates(@Nullable ArrayList<ZoneState> preState)
    {
        if(preState == null) {
            ArrayList<ZoneState> states = new ArrayList<ZoneState>();
            for (ZoneBase zb : All) {
                if(!zb.toDelete)
                states.add(zb.getState());
            }

            Main.config.getConfig().set("Zones.Instances", states);
        }
        else
        {
            Main.config.getConfig().set("Zones.Instances", preState);
        }
        Main.config.saveConfig();
    }

    public static void LoadStates()
    {
        if(Main.config.getConfig().contains("Zones.Instances"))
        {
            ArrayList<ZoneState> states = (ArrayList<ZoneState>) Main.config.getConfig().get("Zones.Instances");
            for(ZoneState zs: states)
            {
                getLogger().info("Loading " + zs.ZoneType);
                try {
                    Class<?> ZoneTypeClass = Class.forName("AT.MSev.Mango.Zones." + zs.ZoneType);
                    Constructor<?> ZoneTypeClassConstructor = ZoneTypeClass.getConstructor(org.bukkit.Location.class, org.bukkit.Location.class, String.class, ArrayList.class);
                    ZoneTypeClassConstructor.newInstance(zs.Bound1, zs.Bound2, zs.Owner, zs.Bypass);
                } catch (Exception e) {
                    getLogger().info("Stateload error ");
                    e.printStackTrace();
                }
            }
        }
    }
}
