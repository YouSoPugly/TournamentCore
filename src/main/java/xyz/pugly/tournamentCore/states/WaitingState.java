package xyz.pugly.tournamentCore.states;

public class WaitingState extends State {

    boolean shouldContinue = false;
    State nextState;

    public WaitingState(State previousState) {
        super(previousState);
    }

    @Override
    public State tick() {
        return shouldContinue ? nextState : null;
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }

    public void setShouldContinue(boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}
