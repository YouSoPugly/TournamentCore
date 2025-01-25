package xyz.pugly.tournamentCore.minigames;

public interface Timed {
    void start();
    void stop();
    void reset();

    int getTime();
    void setTime(int time);

    boolean isRunning();
    void setRunning(boolean running);
}
