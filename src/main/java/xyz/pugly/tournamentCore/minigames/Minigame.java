package xyz.pugly.tournamentCore.minigames;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.pugly.tournamentCore.TournamentCore;

import java.io.File;

public abstract class Minigame implements Timed {

    String name;
    boolean running;
    int time;
    ConfigurationSection config;

    static YamlConfiguration minigameConfig;

    public Minigame(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getDefaultDuration();

    protected YamlConfiguration getMinigameConfig() {
        if (minigameConfig == null) {
            File file = new File(TournamentCore.get().getDataFolder(), "minigames.yml");
            minigameConfig = new YamlConfiguration();
            try {
                minigameConfig.load(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return minigameConfig;
    }
}
