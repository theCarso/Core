package me.thecarso.core.data;

import me.thecarso.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    HashMap<UUID, User> uuidMap;

    public UserManager() {
        uuidMap = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            registerPlayer(player);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                HashMap<UUID, User> newMap = new HashMap<>(uuidMap);
                for (UUID uuid : uuidMap.keySet()) {
                    if (Bukkit.getPlayer(uuid) == null) {
                        uuidMap.get(uuid).save();
                        newMap.remove(uuid);
                    }
                }
                uuidMap = newMap;
            }
        }.runTaskTimer(Core.getInstance(), 20 * 60 * 10, 20 * 60 * 10);
    }

    public void disable() {
        for (User user : uuidMap.values()) {
            user.save();
        }
    }

    public User registerPlayer(Player player) {
        if (!uuidMap.containsKey(player.getUniqueId())) {
            uuidMap.put(player.getUniqueId(), new User(player));
        }
        return uuidMap.get(player.getUniqueId());
    }

    public User getUser(Player player) {
        return uuidMap.get(player.getUniqueId());
    }

}
