package xyz.pugly.tournamentCore.player;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import xyz.pugly.tournamentCore.Team;

import java.util.Collection;
import java.util.HashSet;

import static xyz.pugly.tournamentCore.GameMaster.getTeam;

public class TPlayer extends TUser {

    private int score;
    private Team team;

    public TPlayer(OfflinePlayer player, Team team) {
        super(player);

        this.score = 0;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void tp(Location location) {
        if (getPlayer().isOnline()) {
            getPlayer().getPlayer().teleport(location);
        }
    }

    @Override
    public String toString() {
        return getPlayer().getName() + ": " + score + " | " + getTeam().getId();
    }
}
