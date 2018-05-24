package AT.MSev.Mango;

import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Handler implements Listener {
    @EventHandler
    public void OnLogin(PlayerJoinEvent e)
    {
    }

    @EventHandler
    public void OnRightClick(PlayerInteractEvent e)
    {
        ItemStack compare = e.getPlayer().getInventory().getItemInMainHand();

        if(compare != null && NBTManager.CompareTag(compare, "Custom", new NBTTagString("Interactable"))
                && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            //
            NBTTagString itemName = (NBTTagString) NBTManager.GetTag(compare, "Name");
            if(itemName == null) return;
            CustomItemInteractable cit = (CustomItemInteractable)(CustomItemBase.Get(NBTManager.FromNBTString(itemName)));
            if(cit !=null) cit.RightClickEvent(e);
        }
    }

    @EventHandler
    public void OnDrop(PlayerDropItemEvent e)
    {
        ItemStack compare = e.getItemDrop().getItemStack();

        if (NBTManager.CompareTag(compare, "Custom", new NBTTagString("Interactable"))) {
            //
            NBTTagString itemName = (NBTTagString) NBTManager.GetTag(compare, "Name");
            if (itemName == null) return;
            CustomItemInteractable cit = (CustomItemInteractable) (CustomItemBase.Get(NBTManager.FromNBTString(itemName)));
            if (cit != null) cit.DropEvent(e);
        }
    }

    @EventHandler
    public void OnClick(InventoryClickEvent e)
    {
        ItemStack compare = e.getCurrentItem();

        if (NBTManager.CompareTag(compare, "Custom", new NBTTagString("Menu"))) {
            //
            NBTTagString itemName = (NBTTagString) NBTManager.GetTag(compare, "Name");
            if (itemName == null) return;
            CustomItemMenu cim = (CustomItemMenu)(CustomItemBase.Get(NBTManager.FromNBTString(itemName)));
            if(cim !=null) cim.ClickEvent(e);
        }
    }
}
