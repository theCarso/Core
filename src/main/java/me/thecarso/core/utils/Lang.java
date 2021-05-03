package me.thecarso.core.utils;

import org.bukkit.command.CommandSender;

public enum Lang {

    CONSOLE_SENDER_ERROR("Error: You cannot use this as console."),
    PERMISSION_DENIED("&cYou do not have permission to do this!"),
    INVALID_ARGS("&cInvalid arguments. {1}", true),
    INVALID_ARGS_NOPREFIX("&cInvalid arguments. {1}"),

    GAMEMODE_INVALID_MODE("&cInvalid gamemode. Valid gamemodes are: [creative, survival, spectator, adventure]", true),
    GAMEMODE_INVALID_PLAYER("&cThe player '{1}' is not online!", true),
    GAMEMODE_SUCCESS_TARGET("Your gamemode has been set to {1}.", true),
    GAMEMODE_SUCCESS_SENDER("You have set {1}'s gamemode to {2}", true),

    FLY_INVALID_PLAYER("&cThe player '{1}' is not online!", true),
    FLY_SUCCESS_TARGET("Your flight has been {1}.", true),
    FLY_SUCCESS_SENDER("You have {1} {2}'s flight.", true),

    SPEED_INVALID_SPEED("&cInvalid speed!", true),
    SPEED_SUCCESS("Your {1} speed has been set to &a{2}", true),

    SETSPAWN_SUCCESS("The spawn has been set!", true),
    SPAWN_MISSING("The spawn is not set.", true),
    SPAWN_TELEPORT("You have been teleported to spawn.", true),

    WARP_LIST("Warps: {1}", true),
    WARP_SUCCESS("You have warped to {1}.", true),
    WARP_NO_PERMISSION("You do not have permission to warp here!", true),
    WARP_NONE_FOUND("You do not have access to any warps!", true),
    WARP_INVALID("The warp '{1}' does not exist!", true),

    SETWARP_SUCCESS("You have set the warp {1}.", true),
    DELWARP_SUCCESS("You have deleted the warp {1}.", true),
    DELWARP_ERROR("The warp {1} does not exist!", true),

    MESSAGE_INVALID_PLAYER("&cThe player '{1}' is not online!", false),
    MESSAGE_REPLY_INVALID("&cYou don't have a player to respond to!", false),
    MESSAGE_SENDER("&8[&eme &8-> &e{1}&8] &f{2}"),
    MESSAGE_TARGET("&8[&e{1} &8-> &eme&8] &f{2}"),

    KIT_LIST("Kits: {1}", true),
    KIT_LIST_EMPTY("You do not have any kits.", true),
    KIT_NO_PERMISSION("You do not have permission to use this kit.", true),
    KIT_RECEIVED("You have received the &a{1}&d kit.", true),
    KIT_COOLDOWN("You cannot use this kit for &c{1}&d.", true),
    KIT_NOT_EXIST("The kit '&e{1}&d' does not exist..", true),
    KIT_SENT("You have given &e{1}&d the &e{2}&d kit.", true),
    KIT_INVALID_PLAYER("The player '&e{1}&d' is not online.", true),

    HAT_NOTHING_IN_HAND("&dYou must have something in your hand.", true),
    HAT_SUCCESS("&dYour hat has been set.", true),

    TPA_INVALID_PLAYER("&cThe player '&e{1}&c' is not online."),
    TPA_SELF("&cYou cannot teleport to yourself."),
    TPA_SENT("&dRequested to teleport to &e{1}&d."),
    TPA_RECEIVED("&e{1}&d has requested to teleport to you.<newline>&dType &e/tpaccept&d to accept."),
    TPA_SPAM("&e{1}&c already has a pending /tpa request from you."),
    TPA_TOGGLED("&e{1}&c has teleport requests disabled."),


    TPAHERE_SENT("&dRequested &e{1}&d to teleport to you."),
    TPAHERE_RECEIVED("&e{1}&d has requested that you teleport to them.<newline>&dType &e/tpaccept&d to accept."),

    TPACCEPT_TARGET("&dTeleporting to &e{1}&d..."),
    TPACCEPT_SENDER("&e{1}&d is teleporting to you..."),
    TPACCEPT_NOREQUEST("&cYou do not have any pending /tpa requests."),
    TPACCEPT_OFFLINE("&cThe player '&e{1}&c' is no longer online. Cancelling request..."),

    TPDENY_SENDER("&cDenied &e{1}&c's teleport request."),
    TPDENY_TARGET("&cYour teleport request was denied by &e{1}&c."),
    TPDENY_NOREQUEST("&cYou do not have any pending /tpa requests."),

    TPTOGGLE_SUCCESS("&dTeleport requests have been {1}&d."),

    BACK_NOTFOUND("&cYou do not have a location to go back to."),
    BACK_SUCCESS("&dTeleported back to your previous location."),

    TP_INVALID_PLAYER("&cThe player '&e{1}&c' is not online!"),
    TP_SUCCESS("&dTeleporting to &e{1}&d..."),
    TP_OTHERS("&dTeleporting &e{1} &dto &e{2}&d..."),

    EAT_SUCCESS("&aYour hunger bar has been replenished."),
    EAT_SUCCESS_OTHER("&d{1}&a's hunger bar has been replenished."),
    EAT_INVALID_PLAYER("&cThe player &e{1}&c is not online."),

    ECHEST_INVALID_PLAYER("&cThe player &e{1}&c is not online."),
    ECHEST_OPEN_OTHER("&aOpening &e{1}&a's enderchest..."),

    CLEAR_INVALID_PLAYER("&cThe player &e{1}&c is not online."),
    CLEAR_SUCCESS("&aYour inventory has been cleared."),
    CLEAR_OTHER_SUCCESS("&e{1}&a's inventory has been cleared."),

    PTIME_GET("&aThe current server time is &e{1}&a, and your current player time is &e{2}&a."),
    PTIME_RESET("&aYour clock has been reset to the server's global time."),
    PTIME_SET("&aYour clock has been set to &e{1}&a."),

    PWEATHER_GET("&aYour current player weather is &e{1}&a."),
    PWEATHER_RESET("&aYour weather has been reset to the server's global weather."),
    PWEATHER_SET("&aYour weather has been set to &e{1}&a."),

    CRAFT_SUCCESS("&aOpening workbench..."),

    SOCIALSPY_TOGGLED("&eSocial Spy has been {1}&e."),
    SOCIALSPY_VIEW("&eSS: &8(&e{1} &7-> &e{2}&8) &7{3}"),

    SETHOME_LIMIT("&cYou have reached your limit of &e{1}&c homes."),
    SETHOME_SUCCESS("&aYou have set a home named &e{1}&a."),

    HOME_INVALID("&cYou do not have a home set under that name."),
    HOME_SUCCESS("&dTeleporting to &e{1}&d..."),
    HOME_LIST("&aHomes: &e{1}"),
    HOME_NONE("&cYou do not have any homes set."),

    DELHOME_INVALID("&cYou do not have a home set under that name."),
    DELHOME_SUCCESS("&aDeleted your home &e{1}&a."),

    DISCORD_SERVER("&dJoin our discord server: &e{1}"),

    INVSEE_INVALID_PLAYER("&cThe player '&e{1}&c' is not online."),
    INVSEE_OPEN("&dOpening &e{1}&d's inventory..."),

    FIX_INVALID("&cThat item can not be fixed."),
    FIX_SUCCESS("&aThe item in your hand has been fixed."),
    FIX_COOLDOWN("&cYou cannot fix another item for &e{1}&c."),

    NICK_LIMIT("&cThis nickname is too long."),
    NICK_RESET_PLAYER("&dYour nickname has been reset."),
    NICK_RESET_OTHER("&e{1}&d's nickname has been reset."),
    NICK_SET_PLAYER("&dYour nickname has been set to &e{1}"),
    NICK_SET_OTHER("&e{1}&d's nickname has been set to &e{2}"),
    NICK_INVALID_PLAYER("&cCould not find the player &e{1}&c."),

    SEEN_INVALID_PLAYER("&cThe player &e{1}&c does not exist."),
    SEEN_SUCCESS("&dThe player &e{1}&d was last seen &e{2}&d ago."),
    SEEN_ONLINE("&e{1}&d is online right now."),

    GOD_SUCCESS("&dGod mode has been {1}&d."),

    ;

    private String message;
    private boolean prefixed;

    Lang(String message) {
        this(message, false);
    }

    Lang(String message, boolean prefixed) {
        this.message = message;
        this.prefixed = prefixed;
    }

    public void send(CommandSender player, String... args) {
        if (prefixed) {
            LangUtils.sendPrefixedMessage(player, doArgs(player, args));
        } else {
            LangUtils.sendMessage(player, doArgs(player, args));
        }
    }

    private String doArgs(CommandSender player, String... args) {
        String currentMessage = message;
        for (int i = 0; i < args.length; i++) {
            currentMessage = currentMessage.replace("{" + (i + 1) + "}", args[i]);
        }
        return currentMessage;
    }

}
