package me.thecarso.core.events;

import me.thecarso.core.utils.LangUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        event.setQuitMessage(LangUtils.colorize("&8[&c-&8] &c" + event.getPlayer().getName()));
    }
}
