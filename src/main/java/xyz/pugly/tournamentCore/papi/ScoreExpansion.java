package xyz.pugly.tournamentCore.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import xyz.pugly.tournamentCore.GameMaster;
import xyz.pugly.tournamentCore.player.TPlayer;

public class ScoreExpansion extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "score";
    }

    @Override
    public String getAuthor() {
        return "Tournament";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (GameMaster.getPlayer(player) == null) {
            return "0";
        }
        return GameMaster.getPlayer(player).getScore() + "";
    }
}
