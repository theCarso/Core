package me.thecarso.core.events;

import me.thecarso.core.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEvent implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        event.setRespawnLocation(Core.getInstance().spawn);
    }
}
