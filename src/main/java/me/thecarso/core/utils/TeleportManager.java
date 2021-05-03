package me.thecarso.core.utils;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TeleportManager {

    private static @Getter
    HashMap<UUID, Location> lastTeleport = new HashMap<>();

    public static void teleportPlayer(Player from, Location to) {
        lastTeleport.put(from.getUniqueId(), from.getLocation());
        from.teleport(to);
    }
}
