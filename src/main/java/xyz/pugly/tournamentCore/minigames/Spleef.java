package xyz.pugly.tournamentCore.minigames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.pugly.tournamentCore.TournamentCore;

public class Spleef extends Minigame implements Timed, Listener {

    private World world;
    private Location minCorner;
    private Location maxCorner;

    public Spleef() {
        super("spleef");

        config = getConfig().getConfigurationSection("spleef");
        time = getDefaultDuration();
        running = false;

        world = Bukkit.getWorld(config.getString("world"));
        minCorner = config.getLocation("minCorner");
        maxCorner = config.getLocation("maxCorner");

        Bukkit.getPluginManager().registerEvents(this, TournamentCore.get());
    }

    @Override
    public void start() {
        running = true;
        time = getDefaultDuration();

        for (int x = minCorner.getBlockX(); x <= maxCorner.getBlockX(); x++) {
            for (int z = minCorner.getBlockZ(); z <= maxCorner.getBlockZ(); z++) {
                world.getBlockAt(x, minCorner.getBlockY(), z).setType(Material.SNOW_BLOCK);
            }
        }
    }

    @Override
    public void stop() {
        running = false;
        time = 0;
    }

    @Override
    public void reset() {
        time = getDefaultDuration();
    }

    @Override
    public void tick() {
        time--;
        if (time == 0) return;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if (!running) return;
        if (!e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
        if (e.getClickedBlock().getWorld() != world) return;

        Material m = e.getClickedBlock().getType();
        if (!m.equals(Material.SNOW_BLOCK)) return;

        e.getClickedBlock().setType(Material.AIR);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!running) return;
        if (e.getFrom().getBlock().getLocation() == e.getTo().getBlock().getLocation()) return;
        if (e.getPlayer().getWorld() != world) return;

        if (e.getTo().getY() < minCorner.getY()) {
            //TODO player loses
            System.out.println("Player " + e.getPlayer() + " loses");
        }
    }
}
