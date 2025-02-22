package xyz.pugly.tournamentCore.states;

public abstract class State {

    protected State previousState;
    protected int ticks = 0;

    public State(State previousState) {
        this.previousState = previousState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public State update() {
        ticks++;
        return tick();
    }
    public abstract State tick(); // returns the next state

    public abstract void onEnter();
    public abstract void onExit();
}
