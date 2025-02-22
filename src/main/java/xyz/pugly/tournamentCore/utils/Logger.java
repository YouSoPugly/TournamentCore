package xyz.pugly.tournamentCore.utils;

import xyz.pugly.tournamentCore.TournamentCore;

public class Logger {

    public static void info(String message) {
        TournamentCore.get().getLogger().info(message);
    }

    public static void warn(String message) {
        TournamentCore.get().getLogger().warning(message);
    }

    public static void error(String message) {
        TournamentCore.get().getLogger().severe(message);
    }
}
