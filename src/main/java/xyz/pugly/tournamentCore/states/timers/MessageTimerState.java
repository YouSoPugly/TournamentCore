package xyz.pugly.tournamentCore.states.timers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import xyz.pugly.tournamentCore.states.MessageState;
import xyz.pugly.tournamentCore.states.State;

public class MessageTimerState extends TimerState {

    private MessageState messageState;

    public MessageTimerState(State previousState, State nextState, int time, Component message, Audience audience) {
        super(previousState, nextState, time);
        this.messageState = new MessageState(this, nextState, audience, message);
    }

    @Override
    public State tick() {
        if (super.tick() != null) {
            return messageState;
        }
        return null;
    }
}
