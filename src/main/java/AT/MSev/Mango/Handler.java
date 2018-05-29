package AT.MSev.Mango;

import AT.MSev.Mango.ItemsBlocks.*;
import AT.MSev.Mango.Zones.ZoneBase;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getLogger;

public class Handler implements Listener {
    @EventHandler
    public void OnLogin(PlayerJoinEvent e)
    {
        new CustomBlockBase("BlockTest", Material.DIAMOND_BLOCK).Give(e.getPlayer(), false);
    }

    @EventHandler
    public void OnRightClick(PlayerInteractEvent e)
    {
        ItemStack compare = e.getPlayer().getInventory().getItemInMainHand();

        if(compare != null && NBTManager.CompareTag(compare, "Custom", new NBTTagString("Interactable"))
                && (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            //
            NBTTagString itemName = (NBTTagString) NBTManager.GetTag(compare, "Name");
            if(itemName == null) return;
            getLogger().info(itemName.toString());
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
            if(cim != null) cim.ClickEvent(e);
        }
    }

    @EventHandler
    public void OnPlace(BlockPlaceEvent e)
    {
        ItemStack compare = e.getItemInHand();
        if(NBTManager.CompareTag(compare, "Custom", new NBTTagString("Block")))
        {
            NBTTagString itemName = (NBTTagString) NBTManager.GetTag(compare, "Name");
            if (itemName == null) return;
            CustomBlockBase cbb = (CustomBlockBase)(CustomItemBase.Get(NBTManager.FromNBTString(itemName)));
            if(cbb != null) cbb.PlaceEvent(e);
        }
    }

    @EventHandler
    public void OnBreak(BlockBreakEvent e)
    {
        for(ZoneBase zb : ZoneBase.All)
        {
            if(zb.Setting.IsIn(e.getBlock().getLocation()))
            {
                zb.BreakInZone(e);
            }
        }

        for(CustomBlockBase cbb : CustomBlockBase.Blocks)
        {
            if(e.getBlock().getType().equals(cbb.Physical.getType()))
            {
                for(CustomBlockInstance cbi : new ArrayList<CustomBlockInstance>(cbb.Logicals))
                {
                    if(e.getBlock().getLocation().equals(cbi.Logical))
                    {
                        cbb.BreakEvent(e, cbi);
                    }
                }
            }
        }
    }
}
