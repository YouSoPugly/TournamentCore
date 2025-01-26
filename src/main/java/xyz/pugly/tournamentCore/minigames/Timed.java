package xyz.pugly.tournamentCore.minigames;

public interface Timed {
    void start();
    void stop();
    void reset();
    void tick();

    int getTime();
    void setTime(int time);

    boolean isRunning();
}
