package xyz.pugly.tournamentCore.states.minigames;

import xyz.pugly.tournamentCore.GameMaster;
import xyz.pugly.tournamentCore.Team;
import xyz.pugly.tournamentCore.player.TPlayer;
import xyz.pugly.tournamentCore.states.State;
import xyz.pugly.tournamentCore.states.WaitingState;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

import static me.clip.placeholderapi.util.Msg.broadcast;

public class TestGameState extends GameState {

    public TestGameState(State previousState, Collection<Team> teams) {
        super(previousState, teams);
    }

    @Override
    public State tick() {
        if (ticks % 20 % 5 == 0) {
            broadcast("\u00a7c> Eliminating a player...");

            TPlayer target = (TPlayer) players.toArray()[new Random().nextInt(players.size())];
            target.addScore(20);
            players.remove(target);
            target.sendMessage("\u00a7c> You have been eliminated!");
        }

        if (players.isEmpty()) return new WaitingState(this);
        return null;
    }

    @Override
    public void onEnter() {
        super.onEnter();

        broadcast("\u00a7a> Welcome to the test game!");
    }

    @Override
    public void onExit() {
        super.onExit();
        broadcast("\u00a7c> The test game has ended!");
    }
}
