package xyz.pugly.tournamentCore.states;

public abstract class State {

    protected State previousState;

    public State(State previousState) {
        this.previousState = previousState;
    }

    public State getPreviousState() {
        return previousState;
    }

    public abstract State tick(); // returns the next state

    public abstract void onEnter();
    public abstract void onExit();
}
