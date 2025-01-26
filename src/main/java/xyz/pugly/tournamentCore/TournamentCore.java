package xyz.pugly.tournamentCore;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.pugly.tournamentCore.player.TPlayer;

import java.util.HashSet;
import java.util.Set;

public final class TournamentCore extends JavaPlugin {

    private MiniMessage miniMessage = MiniMessage.miniMessage();

    private static TournamentCore instance;

    private Set<Team> teams = new HashSet<>();

    private static BukkitRunnable gameLoop;

    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        saveDefaultConfig();
        reloadConfig();

        loadTeams();

        gameLoop = new BukkitRunnable() {
            @Override
            public void run() {
                GameMaster.tick();
            }
        };
        gameLoop.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static TournamentCore get() {
        return instance;
    }

    public void loadTeams() {
        ConfigurationSection teams = getConfig().getConfigurationSection("teams");
        if (teams == null) {
            getLogger().warning("No teams found in config");
            return;
        }

        for (String teamName : teams.getKeys(false)) {
            ConfigurationSection team = teams.getConfigurationSection(teamName);
            if (team == null) {
                getLogger().warning("No team found for " + teamName);
                continue;
            }

            Team newTeam = new Team(teamName, Color.fromRGB(team.getInt("color")));

            for (String playerName : team.getStringList("players")) {
                newTeam.addPlayer(new TPlayer(Bukkit.getOfflinePlayer(playerName), newTeam));
            }

        }
    }

    private void registerCommands() {
        // GAME COMMANDS
        CommandAPICommand gameStart = new CommandAPICommand("start")
                .withArguments(new MultiLiteralArgument("games"))
                .executes((sender, args) -> {
                    // Start the game
                    return 1;
                });

        CommandAPICommand gameEnd = new CommandAPICommand("end")
                .executes((sender, args) -> {
                    if (!GameMaster.isGameRunning()) {
                        return 1;
                    }
                    GameMaster.endGame();
                    return 1;
                });

        new CommandAPICommand("game")
                .withSubcommands(gameStart, gameEnd)
                .register();

        // TOURNAMENT CONTROLLER COMMANDS

        new CommandAPICommand("tc")
                .register();
    }
}
