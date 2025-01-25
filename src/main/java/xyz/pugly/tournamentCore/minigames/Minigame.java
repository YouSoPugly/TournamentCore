package xyz.pugly.tournamentCore.minigames;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import xyz.pugly.tournamentCore.TournamentCore;

public abstract class Minigame implements Timed {

    protected final String name;
    protected boolean running;
    protected int time;

    protected ConfigurationSection config;

    public Minigame(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getDefaultDuration() {
        return config.getInt("duration");
    }

    protected ConfigurationSection getConfig() {
        return TournamentCore.get().getConfig();
    }
}
