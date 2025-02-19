package xyz.pugly.tournamentCore;

import org.bukkit.OfflinePlayer;
import xyz.pugly.tournamentCore.player.TPlayer;
import xyz.pugly.tournamentCore.player.TSpectator;
import xyz.pugly.tournamentCore.player.TStaff;
import xyz.pugly.tournamentCore.states.State;
import xyz.pugly.tournamentCore.states.WaitingState;

import java.util.HashSet;
import java.util.Set;

public class GameMaster {

    private static State currentState = new WaitingState(null);

    private static final Set<Team> teams = new HashSet<>();
    private static final Set<TSpectator> spectators = new HashSet<>();
    private static final Set<TStaff> staff = new HashSet<>();

    public static Set<Team> getTeams() {
        return teams;
    }

    public static Set<TSpectator> getSpectators() {
        return spectators;
    }

    public static Set<TStaff> getStaff() {
        return staff;
    }

    public static Set<TPlayer> getPlayers() {
        Set<TPlayer> players = new HashSet<>();
        for (Team team : teams) {
            players.addAll(team.getPlayers());
        }
        return players;
    }

    public static void addTeam(Team team) {
        teams.add(team);
    }

    public static void addSpectator(TSpectator spectator) {
        spectators.add(spectator);
    }

    public static void addStaff(TStaff staff) {
        GameMaster.staff.add(staff);
    }

    public static void removeTeam(Team team) {
        teams.remove(team);
    }

    public static void removeSpectator(TSpectator spectator) {
        spectators.remove(spectator);
    }

    public static void removeStaff(TStaff staff) {
        GameMaster.staff.remove(staff);
    }

    public static void addPlayer(TPlayer player, Team team) {
        team.addPlayer(player);
    }

    public static TPlayer getPlayer(OfflinePlayer player) {
        for (Team team : teams) {
            for (TPlayer tp : team.getPlayers()) {
                if (tp.getPlayer() == player) {
                    return tp;
                }
            }
        }
        return null;
    }

    public static Team getTeam(TPlayer player) {
        for (Team team : teams) {
            if (team.getPlayers().contains(player)) {
                return team;
            }
        }
        return null;
    }

    public static Team getTeam(OfflinePlayer player) {
        return getTeam(getPlayer(player));
    }

    public static void tick() {
        State nextState = currentState.tick();
        if (nextState != null) {
            currentState.onExit();
            currentState = nextState;
            currentState.onEnter();
        }
    }

}
