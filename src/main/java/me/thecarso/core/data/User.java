package me.thecarso.core.data;

import lombok.Getter;
import lombok.Setter;
import me.thecarso.core.Core;
import me.thecarso.core.utils.CFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class User {

    UUID uuid;
    CFile file;

    private @Getter
    HashMap<String, Location> homes;

    private @Getter
    @Setter
    String nick;

    private @Getter
    @Setter
    boolean tpEnabled;

    private @Getter
    @Setter
    long nextFix;

    User(Player player) {
        UUID uuid = player.getUniqueId();
        this.uuid = uuid;
        file = new CFile(Core.getInstance(), "players/" + uuid.toString(), false);

        if (file.getFile().isSet("nick")) {
            nick = file.getFile().getString("nick");
        } else {
            nick = player.getName();
        }

        if (file.getFile().isSet("nextFix")) {
            nextFix = file.getFile().getLong("nextFix");
        } else {
            nextFix = 0;
        }

        if (file.getFile().isSet("tpEnabled")) {
            tpEnabled = file.getFile().getBoolean("tpEnabled");
        } else {
            tpEnabled = true;
        }
        loadHomes();
    }

    public void save() {
        file.getFile().set("nick", nick);
        file.getFile().set("nextFix", nextFix);
        file.getFile().set("tpEnabled", tpEnabled);
        saveHomes();

        file.saveFile();
    }

    private void loadHomes() {
        homes = new HashMap<>();
        if (file.getFile().isSet("homes")) {
            for (String string : file.getFile().getConfigurationSection("homes").getKeys(false)) {
                String[] split = file.getFile().getString("homes." + string).split(";");
                String worldName;
                double x, y, z;
                float yaw, pitch;
                Location location;
                try {
                    worldName = split[0];
                    x = Double.parseDouble(split[1]);
                    y = Double.parseDouble(split[2]);
                    z = Double.parseDouble(split[3]);

                    yaw = Float.parseFloat(split[4]);
                    pitch = Float.parseFloat(split[5]);
                    location = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
                } catch (Exception ex) {
                    Bukkit.getLogger().info("Player " + uuid + " has a corrupted home (" + string + ").");
                    Bukkit.getLogger().info("Log:");
                    ex.printStackTrace();
                    continue;
                }
                homes.put(string.toLowerCase(), location);

            }
        }
    }

    private void saveHomes() {
        file.getFile().set("homes", null);
        for (String homeName : homes.keySet()) {
            Location location = homes.get(homeName);
            file.getFile().set("homes." + homeName + "", location.getWorld().getName() + ";" +
                    location.getX() + ";" +
                    location.getY() + ";" +
                    location.getZ() + ";" +
                    location.getYaw() + ";" +
                    location.getPitch());
        }
    }


    public static int getHomeAmount(Player player) {
        for (int i = 100; i > 0; i--) {
            if (player.hasPermission("core.home." + i)) return i;
        }
        return 0;
    }
}
