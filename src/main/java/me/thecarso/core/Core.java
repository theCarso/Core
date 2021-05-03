package me.thecarso.core;

import lombok.Getter;
import me.thecarso.core.cmds.*;
import me.thecarso.core.data.UserManager;
import me.thecarso.core.events.*;
import me.thecarso.core.kits.Kit;
import me.thecarso.core.kits.KitManager;
import me.thecarso.core.utils.CFile;
import me.thecarso.core.warps.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private static @Getter
    Core instance;
    private static @Getter
    CFile configFile;
    private static @Getter
    WarpManager warpManager;
    private static @Getter
    KitManager kitManager;
    private static @Getter
    UserManager userManager;

    public @Getter
    Location spawn;

    @Override
    public void onEnable() {
        instance = this;
        configFile = new CFile(this, "config", true);
        registerEvents();
        registerCommands();

        loadSpawn();
        userManager = new UserManager();
        warpManager = new WarpManager();
        kitManager = new KitManager();
    }

    @Override
    public void onDisable() {
        warpManager.saveAllWarps();
        for (Kit kit : kitManager.getKits().values()) {
            kit.saveKit();
        }
        userManager.disable();
    }

    private void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinEvent(), this);
        pluginManager.registerEvents(new LeaveEvent(), this);
        pluginManager.registerEvents(new RespawnEvent(), this);
        pluginManager.registerEvents(new PingEvent(), this);
    }

    private void registerCommands() {
        getCommand("gamemode").setExecutor(new GameModeCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("delwarp").setExecutor(new DelWarpCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("kit").setExecutor(new KitCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("raw").setExecutor(new RawCommand());
        getCommand("hat").setExecutor(new HatCommand());
        getCommand("tp").setExecutor(new TPCommand());
        getCommand("tpa").setExecutor(new TPACommand());
        getCommand("tpahere").setExecutor(new TPAHereCommand());
        getCommand("tpaccept").setExecutor(new TPAcceptCommand());
        getCommand("tpdeny").setExecutor(new TPDenyCommand());
        getCommand("tptoggle").setExecutor(new TPToggleCommand());
        getCommand("back").setExecutor(new BackCommand());
        getCommand("eat").setExecutor(new EatCommand());
        getCommand("echest").setExecutor(new EChestCommand());
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("ptime").setExecutor(new PTimeCommand());
        getCommand("craft").setExecutor(new CraftCommand());
        getCommand("socialspy").setExecutor(new SocialSpyCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("pweather").setExecutor(new PWeatherCommand());
        getCommand("fix").setExecutor(new FixCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("seen").setExecutor(new SeenCommand());
        getCommand("sendraw").setExecutor(new SendRawCommand());
        getCommand("god").setExecutor(new GodCommand());
    }

    private void loadSpawn() {
        if (configFile.getFile().isSet("spawn")) {
            ConfigurationSection section = configFile.getFile().getConfigurationSection("spawn");
            spawn = new Location(Bukkit.getWorld(section.getString("world")),
                    section.getDouble("x"),
                    section.getDouble("y"),
                    section.getDouble("z"),
                    (float) section.getDouble("yaw"),
                    (float) section.getDouble("pitch"));
        }
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
        ConfigurationSection section = configFile.getFile();
        section.set("spawn.world", spawn.getWorld().getName());
        section.set("spawn.x", spawn.getX());
        section.set("spawn.y", spawn.getY());
        section.set("spawn.z", spawn.getZ());
        section.set("spawn.yaw", spawn.getYaw());
        section.set("spawn.pitch", spawn.getPitch());

        configFile.saveFile();
    }

}
