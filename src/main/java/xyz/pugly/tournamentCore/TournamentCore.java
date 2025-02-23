package xyz.pugly.tournamentCore;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.annotations.Command;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.pugly.tournamentCore.player.TPlayer;
import xyz.pugly.tournamentCore.states.minigames.TestGameState;
import xyz.pugly.tournamentCore.utils.Logger.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static xyz.pugly.tournamentCore.utils.Logger.info;
import static xyz.pugly.tournamentCore.utils.Logger.warn;

public final class TournamentCore extends JavaPlugin {

    private MiniMessage miniMessage = MiniMessage.miniMessage();

    private static TournamentCore instance;

    private Set<Team> teams = new HashSet<>();

    private static BukkitRunnable gameLoop;

    @Override
    public void onLoad() {
        instance = this;
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).verboseOutput(true)); // Load with verbose output
    }

    @Override
    public void onEnable() {
        info("Enabling TournamentCore");
        CommandAPI.onEnable();

        // TODO temp delete old config
        File f = new File(getDataFolder(), "config.yml");
        f.delete();

        saveDefaultConfig();
        reloadConfig();

        saveResource("minigames.yml", true);

        loadTeams();

        gameLoop = new BukkitRunnable() {
            @Override
            public void run() {
                GameMaster.tick();
            }
        };
        gameLoop.runTaskTimer(this, 0, 20);

        registerCommands();
        info("TournamentCore enabled");
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    public static TournamentCore get() {
        return instance;
    }

    public void loadTeams() {
        ConfigurationSection teams = getConfig().getConfigurationSection("teams");
        if (teams == null) {
            warn("No teams found in config");
            return;
        }

        for (String teamName : teams.getKeys(false)) {
            ConfigurationSection team = teams.getConfigurationSection(teamName);
            if (team == null) {
                warn("No team found for " + teamName);
                continue;
            }

            Team newTeam = new Team(teamName, team.getString("name"), Color.fromRGB(Integer.parseInt(team.getString("color"), 16)));

            for (String playerName : team.getStringList("players")) {
                newTeam.addPlayer(new TPlayer(Bukkit.getOfflinePlayer(playerName), newTeam));
            }

            GameMaster.addTeam(newTeam);
        }
    }

    private void registerCommands() {
        // GAME COMMANDS
//        CommandAPICommand gameStart = new CommandAPICommand("start")
//                .withArguments(new MultiLiteralArgument("games"))
//                .executes((sender, args) -> {
//                    // Start the game
//                    return 1;
//                });

//        CommandAPICommand gameEnd = new CommandAPICommand("end")
//                .executes((sender, args) -> {
//                    if (!GameMaster.isGameRunning()) {
//                        return 1;
//                    }
//                    GameMaster.endGame();
//                    return 1;
//                });
//
//        new CommandAPICommand("game")
//                .withPermission("tc.game")
//                .withSubcommands(gameEnd)
//                .register();

        // TOURNAMENT CONTROLLER COMMANDS

        CommandAPICommand test = new CommandAPICommand("test")
                .withPermission("tc.test")
                .executes((sender, args) -> {
                    GameMaster.setState(new TestGameState(GameMaster.getCurrentState(), GameMaster.getTeams()));
                    sender.sendMessage("Test game started");
                    return 1;
                });

        CommandAPICommand players = new CommandAPICommand("players")
                .executes((sender, args) -> {
                    sender.sendMessage("Players:");
                    for (TPlayer player : GameMaster.getPlayers()) {
                        sender.sendMessage(player.toString());
                    }
                    return 1;
                });

        CommandAPICommand teams = new CommandAPICommand("teams")
                .executes((sender, args) -> {
                    sender.sendMessage("Teams:");
                    for (Team team : GameMaster.getTeams()) {
                        sender.sendMessage(team.getName());
                    }
                    return 1;
                });

        new CommandAPICommand("tc")
                .withSubcommands(test, players, teams)
                .register();
    }
}
