package AT.MSev.Mango;

import AT.MSev.Mango.Commands.CommandCreateZoneProtect;
import AT.MSev.Mango.Commands.CommandZone;
import AT.MSev.Mango.Zones.ZoneBase;
import AT.MSev.Mango.Zones.ZoneState;
import AT.MSev.Mango.Zones.ZoneVision;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;


public class Main extends JavaPlugin {
    public static NamespacedKey key;
    public static Main config;
    public static File Folder;

    static {
        ConfigurationSerialization.registerClass(ZoneState.class, "ZoneState");
    }

    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());
        config = this;
        Folder = this.getDataFolder();
        CommandCreateZoneProtect CCZP = new CommandCreateZoneProtect();
        getServer().getPluginCommand("startProtect").setExecutor(CCZP);
        getServer().getPluginCommand("endProtect").setExecutor(CCZP);
        getServer().getPluginCommand("zone").setExecutor(new CommandZone());

        getServer().getPluginManager().registerEvents(new Handler(), this);

        ZoneBase.LoadStates();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new ZoneVision(), 0L, 10L);
    }
    @Override
    public void onDisable() {

    }
}
