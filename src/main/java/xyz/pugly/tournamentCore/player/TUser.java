package xyz.pugly.tournamentCore.player;

import org.bukkit.OfflinePlayer;

public abstract class TUser {

    protected final OfflinePlayer player;

    public TUser(OfflinePlayer player) {
        this.player = player;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

}
