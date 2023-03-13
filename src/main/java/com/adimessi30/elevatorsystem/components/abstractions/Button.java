package com.adimessi30.elevatorsystem.components.abstractions;

import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class Button {
    private final RequestDispatcher dispatcher;
    @Getter
    @Setter
    private boolean isPressed;
    @Getter
    @Setter
    private boolean isDisabled;

    public void sendRequestToDispatcher(ElevatorRequest elevatorRequest) {
        dispatcher.dispatch(elevatorRequest);
    }

    public boolean canPressButton() {
        return !(this.isDisabled() || this.isPressed());
    }
}
