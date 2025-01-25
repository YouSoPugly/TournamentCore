package xyz.pugly.tournamentCore;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import xyz.pugly.tournamentCore.player.TPlayer;

import java.util.HashSet;
import java.util.Set;

public class Team {

    private final String name;
    private final Color color;

    private final Set<TPlayer> players = new HashSet<>();

    public Team(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void addPlayer(TPlayer player) {
        players.add(player);
    }

    public void removePlayer(TPlayer player) {
        players.remove(player);
    }

    public Set<TPlayer> getPlayers() {
        return players;
    }

    public int getScore() {
        return players.stream().mapToInt(TPlayer::getScore).sum();
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return MiniMessage.miniMessage().deserialize(name).toString();
    }
}
