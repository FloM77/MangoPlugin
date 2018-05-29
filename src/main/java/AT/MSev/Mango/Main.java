package AT.MSev.Mango;

import AT.MSev.Mango.Commands.CommandCreateZoneProtect;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {
    public static NamespacedKey key;
    public static Main config;
    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());
        config = this;
        CommandCreateZoneProtect CCZP = new CommandCreateZoneProtect();
        getServer().getPluginCommand("startProtect").setExecutor(CCZP);
        getServer().getPluginCommand("endProtect").setExecutor(CCZP);

        getServer().getPluginManager().registerEvents(new Handler(), this);
    }
    @Override
    public void onDisable() {

    }
}
