package me.thecarso.core.warps;

import lombok.Getter;
import me.thecarso.core.Core;
import me.thecarso.core.utils.CFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarpManager {

    CFile warpFile;
    private @Getter
    HashMap<String, Warp> warps;

    public WarpManager() {
        warpFile = new CFile(Core.getInstance(), "warps", false);
        warps = new HashMap<>();

        loadWarps();
    }

    private void loadWarps() {
        for (String name : warpFile.getFile().getKeys(false)) {
            ConfigurationSection section = warpFile.getFile().getConfigurationSection(name);
            Location location = new Location(Bukkit.getWorld(section.getString("world")),
                    section.getDouble("x"),
                    section.getDouble("y"),
                    section.getDouble("z"),
                    (float) section.getDouble("yaw"),
                    (float) section.getDouble("pitch"));
            boolean usePermission = section.getBoolean("usePermission");

            warps.put(name.toLowerCase(), new Warp(name, location, usePermission));
        }
    }

    public void saveAllWarps() {
        for (String string : warpFile.getFile().getKeys(false)) {
            warpFile.getFile().set(string, null);
        }

        for (String name : warps.keySet()) {
            Warp warp = warps.get(name);
            ConfigurationSection section = warpFile.getFile();
            section.set(name + ".world", warp.location.getWorld().getName());
            section.set(name + ".x", warp.location.getX());
            section.set(name + ".y", warp.location.getY());
            section.set(name + ".z", warp.location.getZ());
            section.set(name + ".yaw", warp.location.getYaw());
            section.set(name + ".pitch", warp.location.getPitch());
            section.set(name + ".usePermission", warp.usePermission);
        }
        warpFile.saveFile();
    }

    public List<String> getAvailableWarps(Player player) {
        ArrayList<String> list = new ArrayList<>();
        for (String string : warps.keySet()) {
            Warp warp = warps.get(string);
            if (!warp.usePermission) {
                list.add(warp.getName());
            } else {
                if (player.hasPermission(warp.getPermission())) {
                    list.add(warp.getName());
                }
            }
        }
        return list;
    }

}
