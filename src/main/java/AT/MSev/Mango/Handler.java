package AT.MSev.Mango;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Handler implements Listener {
    @EventHandler
    public void OnLogin(PlayerJoinEvent e)
    {
    }

    @EventHandler
    public void OnRightClick(PlayerInteractEvent e)
    {
        if(e.getPlayer().getInventory().getItemInMainHand()!=null && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(e.getPlayer().getInventory().getItemInMainHand());
            if (nmsCopy.hasTag() && nmsCopy.getTag().getString("Custom").equals("Interactable")) {
                String itemName = nmsCopy.getTag().getString("Name");
                CustomItemInteractable cit = (CustomItemInteractable)(CustomItemBase.Get(itemName));
                if(cit !=null) cit.RightClickEvent(e);
            }
        }
    }

    @EventHandler
    public void OnDrop(PlayerDropItemEvent e)
    {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(e.getItemDrop().getItemStack());
        if (nmsCopy.hasTag() && nmsCopy.getTag().getString("Custom").equals("Interactable")) {
            String itemName = nmsCopy.getTag().getString("Name");
            CustomItemInteractable cit = (CustomItemInteractable)(CustomItemBase.Get(itemName));
            if(cit !=null) cit.DropEvent(e);
        }
    }

    @EventHandler
    public void OnClick(InventoryClickEvent e) {
        net.minecraft.server.v1_12_R1.ItemStack nmsCopy = CraftItemStack.asNMSCopy(e.getCurrentItem());
        if (nmsCopy.hasTag() && nmsCopy.getTag().getString("Custom").equals("Menu")) {
            String itemName = nmsCopy.getTag().getString("Name");
            CustomItemMenu cim = (CustomItemMenu)(CustomItemBase.Get(itemName));
            if(cim !=null) cim.ClickEvent(e);
        }
    }
}
