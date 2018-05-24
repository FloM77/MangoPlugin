package AT.MSev.Mango;

import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class CustomItemInteractable extends CustomItemBase {
    protected int Cooldown = 2000;
    ArrayList<Player> OnCooldown = new ArrayList<Player>();
    public CustomItemInteractable(String name, Material appearance)
    {
        super(name, appearance);
        SetTag("Custom", new NBTTagString("Interactable"));
    }

    protected Boolean RightClickCanceled = false;
    public void RightClickEvent(PlayerInteractEvent e)
    {
        RightClickCanceled = !IsUseable(e.getPlayer());
        if(!RightClickCanceled)
        {
            StartCooldown(e.getPlayer());
        }
    }

    public void DropEvent(PlayerDropItemEvent e)
    {

    }

    void StartCooldown(final Player p)
    {
        OnCooldown.add(p);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(Cooldown);
                    OnCooldown.remove(p);
                    CooldownEvent(p);
                }catch(Exception ex){}
            }
        }).start();
    }

    protected Boolean IsUseable(Player p)
    {
        if(!OnCooldown.contains(p)) {return  true;}
        else {return false;}
    }

    protected void CooldownEvent(final Player p)
    {
        p.sendMessage(ChatColor.GREEN + Name + " is off Cooldown.");
    }
}
