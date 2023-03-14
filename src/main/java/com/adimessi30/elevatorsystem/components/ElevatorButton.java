package com.adimessi30.elevatorsystem.components;

import com.adimessi30.elevatorsystem.components.abstractions.Button;
import com.adimessi30.elevatorsystem.enums.Direction;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static com.adimessi30.elevatorsystem.enums.Direction.DOWN;
import static com.adimessi30.elevatorsystem.enums.Direction.UP;

@Slf4j
public class ElevatorButton extends Button {
    @Setter
    @Getter
    private int mappedFloor;

    public ElevatorButton(RequestDispatcher dispatcher) {
        super(dispatcher);
    }

    public void requestFloor(int elevatorId, int currentFloor) {
        if (canPressButton(currentFloor)) {
            this.setPressed(true);
            press(currentFloor, elevatorId, false);
        }
    }

    public void cancel(int elevatorId, int currentFloor) {
        if (isPressed())
            press(currentFloor, elevatorId, true);
    }

    protected void press(int currentFloor, int elevatorId, boolean isCancellation) {
        super.press(elevatorId, mappedFloor, getDirection(currentFloor), isCancellation);
    }

    private Direction getDirection(int currentFloor) {
        return this.mappedFloor > currentFloor ? UP : DOWN;
    }

    protected void postConstruct() {
        log.debug("New elevatorButton was created.......");
    }

    protected boolean canPressButton(int currentFloor) {
        return super.canPressButton() && !Objects.equals(mappedFloor, currentFloor);
    }
}
