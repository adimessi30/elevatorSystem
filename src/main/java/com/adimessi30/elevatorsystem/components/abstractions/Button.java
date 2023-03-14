package com.adimessi30.elevatorsystem.components.abstractions;

import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import com.adimessi30.elevatorsystem.enums.Direction;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.adimessi30.elevatorsystem.helpers.HookFactory.getHookForButtonCallback;

@RequiredArgsConstructor
public abstract class Button extends Resettable {
    private final RequestDispatcher dispatcher;
    @Getter
    @Setter
    private boolean isPressed;
    @Getter
    @Setter
    private boolean isDisabled;

    protected void press(int elevatorId, int floorNumber, @NonNull Direction direction, boolean isCancellation) {
        ElevatorRequest elevatorRequest = ElevatorRequest.builder()
                .elevatorId(elevatorId)
                .floorNumber(floorNumber)
                .direction(direction)
                .buttonCallback(getHookForButtonCallback(this))
                .cancellation(isCancellation)
                .build();
        dispatcher.dispatch(elevatorRequest);
    }

    protected boolean canPressButton() {
        return !(this.isDisabled() || this.isPressed());
    }

    @Override
    public void reset() {
        isPressed = false;
    }
}
