package xyz.pugly.tournamentCore.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import xyz.pugly.tournamentCore.GameMaster;
import xyz.pugly.tournamentCore.player.TPlayer;

public class TeamExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "team";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Tournament";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (GameMaster.getPlayer(player) == null) {
            return "NO TEAM";
        }
        return ((TextComponent) GameMaster.getTeam(player).getName()).content();
    }
}
