package AT.MSev.Mango;

import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class CustomItemMenu extends CustomItemBase {
    public CustomItemMenu(String name, Material appearance)
    {
        super(name, appearance);
        SetTag("Custom", new NBTTagString("Menu"));
    }

    public void ClickEvent(InventoryClickEvent e)
    {
        e.setCancelled(true);
    }
}
