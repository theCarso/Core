package me.thecarso.core.events;

import me.thecarso.core.Core;
import me.thecarso.core.data.User;
import me.thecarso.core.utils.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.security.auth.callback.LanguageCallback;
import java.util.UUID;

public class JoinEvent implements Listener {

    @EventHandler
    public void playerJoinPre(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        try {
            User user = Core.getUserManager().registerPlayer(player);
            player.setDisplayName(LangUtils.colorize(user.getNick()));
        } catch (Exception ex) {
            event.setKickMessage(ChatColor.RED + "There was an error loading your player data. Please contact an admin immediately.");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.setBedSpawnLocation(Core.getInstance().spawn);
            player.teleport(Core.getInstance().spawn);
        }


        event.setJoinMessage(LangUtils.colorize("&8[&a+&8] &a" + event.getPlayer().getName()));
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                for (int i = 0; i < 25; i++) {
                    LangUtils.sendMessage(player, "");
                }
                LangUtils.sendMessage(player, "");
                LangUtils.sendMessage(player, "            &dWelcome to &5&lIdleMC          ");
                LangUtils.sendMessage(player, "");
                LangUtils.sendMessage(player, "         &dType &5/help&d to get started!        ");
                LangUtils.sendMessage(player, "     &dWebsite: &5http://store.idlemc.org     ");
                LangUtils.sendMessage(player, "    &5/discord &dto join our discord server!    ");
                LangUtils.sendMessage(player, "");
                LangUtils.sendMessage(player, "");
            }
        }.runTaskLater(Core.getInstance(), 2);

    }


}
