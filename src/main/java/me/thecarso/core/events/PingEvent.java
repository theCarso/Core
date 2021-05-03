package me.thecarso.core.events;

import me.thecarso.core.Core;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingEvent implements Listener {

    private String motdString;

    public PingEvent() {
        motdString = Core.getConfigFile().getFile().getString("motd");
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(LangUtils.colorize(motdString));
    }
}
