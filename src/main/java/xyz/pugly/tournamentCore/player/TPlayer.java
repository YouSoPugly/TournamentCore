package xyz.pugly.tournamentCore.player;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import xyz.pugly.tournamentCore.Team;

import java.util.Collection;
import java.util.HashSet;

public class TPlayer extends TUser {

    private int score;

    public TPlayer(OfflinePlayer player, Team team) {
        super(player);

        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void removeScore(int score) {
        this.score -= score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void tp(Location location) {
        if (getPlayer().isOnline()) {
            getPlayer().getPlayer().teleport(location);
        }
    }
}
