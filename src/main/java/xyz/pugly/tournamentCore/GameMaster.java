package xyz.pugly.tournamentCore;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.pugly.tournamentCore.minigames.Minigame;
import xyz.pugly.tournamentCore.player.TPlayer;
import xyz.pugly.tournamentCore.player.TSpectator;
import xyz.pugly.tournamentCore.player.TStaff;
import xyz.pugly.tournamentCore.player.TUser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class GameMaster {

    private static Minigame currentGame = null;
    private static int countdown = 0;

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

    public static boolean isGameRunning() {
        return currentGame.isRunning();
    }

    public static Minigame getCurrentGame() {
        return currentGame;
    }

    public static void setGame(Minigame game) {
        currentGame = game;
        currentGame.setTime(currentGame.getDefaultDuration());
    }

    public static void startGame() {
        if (isGameRunning()) {
            throw new IllegalStateException("Game is already running");
        }

        if (currentGame == null) {
            throw new IllegalStateException("No game set");
        }

        countdown = 15;
    }

    public static void endGame() {
        if (!isGameRunning()) {
            throw new IllegalStateException("Game is not running");
        }
        currentGame.stop();
        currentGame = null;
    }

    public static void tick() {
        if (currentGame == null) {
            return;
        }

        if (isGameRunning()) {
            currentGame.tick();
            return;
        }

        if (countdown > 0) {
            countdown--;
            //TODO countdown visuals
            Set<TUser> users = new HashSet<>();
            users.addAll(getPlayers());
            users.addAll(getSpectators());
            for (TUser user : users) {
                Player p = user.getPlayer().getPlayer();
                if (p != null) {
                    p.sendMessage("Game starting in " + countdown + " seconds");
                }
            }
            return;
        }

        if (countdown == 0 && !isGameRunning()) {
            currentGame.start();
        }
    }

}
