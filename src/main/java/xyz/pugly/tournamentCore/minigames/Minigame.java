package xyz.pugly.tournamentCore.minigames;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.pugly.tournamentCore.TournamentCore;

import java.io.File;

public abstract class Minigame implements Timed {

    protected final String name;
    protected boolean running;
    protected int time;

    static ConfigurationSection config = new YamlConfiguration();

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
        if (config == null) {
            File file = new File(TournamentCore.get().getDataFolder(), "minigames.yml");
            config = YamlConfiguration.loadConfiguration(file);
        }
        return config;
    }
}
