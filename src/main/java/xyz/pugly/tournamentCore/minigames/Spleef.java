package xyz.pugly.tournamentCore.minigames;

public class Spleef extends Minigame {


    public Spleef() {
        super("spleef");

        config = getConfig().getConfigurationSection("spleef");
        time = getDefaultDuration();
        running = false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void reset() {

    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public void setTime(int time) {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void setRunning(boolean running) {

    }
}
