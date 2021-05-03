package me.thecarso.core.warps;

import me.thecarso.core.Core;
import me.thecarso.core.utils.Lang;
import me.thecarso.core.utils.TeleportManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Warp {

    String name;
    Location location;
    boolean usePermission;

    public Warp(String name, Location location, boolean usePermission) {
        this.name = name;
        this.location = location;
        this.usePermission = usePermission;
    }

    public void teleport(Player player) {
        TeleportManager.teleportPlayer(player, location);
        Lang.WARP_SUCCESS.send(player, name);
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        if (!usePermission) return null;

        return "core.warp." + name.toLowerCase();
    }
}
