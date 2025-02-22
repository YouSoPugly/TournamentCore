package xyz.pugly.tournamentCore.states.minigames;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import xyz.pugly.tournamentCore.Team;
import xyz.pugly.tournamentCore.TournamentCore;
import xyz.pugly.tournamentCore.player.TPlayer;
import xyz.pugly.tournamentCore.states.State;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.Collection;
import java.util.HashSet;

public abstract class GameState extends State implements Listener {

    protected Collection<Team> teams;
    protected Collection<TPlayer> players;

    public GameState(State previousState, Collection<Team> teams) {
        super(previousState);

        this.teams = teams;
        this.players = new HashSet<>();
        for (Team team : teams) {
            players.addAll(team.getPlayers());
        }

    }

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onExit() {
        HandlerList.unregisterAll(this);
    }

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onEnter() {
        Bukkit.getServer().getPluginManager().registerEvents(this, TournamentCore.get());
    }
}
