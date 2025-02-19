package xyz.pugly.tournamentCore.states.timers;

import xyz.pugly.tournamentCore.states.State;

public class TimerState extends State {

    private State nextState;
    private int time; // in ticks

    public TimerState(State previousState, State nextState, int time) {
        super(previousState);
        this.nextState = nextState;
        this.time = time;
    }

    @Override
    public State tick() {
        time--;

        if (time <= 0) {
            return nextState;
        }

        return null;
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }
}
