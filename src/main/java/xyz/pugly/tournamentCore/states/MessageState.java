package xyz.pugly.tournamentCore.states;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;

public class MessageState extends State {

    private Audience audience;
    private Component message;
    private State nextState;

    public MessageState(State previousState, State nextState, Audience audience, Component message) {
        super(previousState);

        this.nextState = nextState;
        this.audience = audience;
        this.message = message;
    }

    @Override
    public State tick() {
        return nextState;
    }

    @Override
    public void onEnter() {
        audience.sendMessage(message);
        audience.showTitle(Title.title(message, Component.empty()));
    }

    @Override
    public void onExit() {

    }
}
