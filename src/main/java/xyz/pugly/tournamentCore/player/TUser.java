package xyz.pugly.tournamentCore.player;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;

public abstract class TUser {

    protected final OfflinePlayer player;

    public TUser(OfflinePlayer player) {
        this.player = player;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public void sendMessage(Component message) {
        if (player.getPlayer() != null)
            player.getPlayer().sendMessage(message);
    }

    public void sendMessage(String message) {
        if (player.getPlayer() != null)
            player.getPlayer().sendMessage(message);
    }

}
