package com.adimessi30.elevatorsystem.components;

import com.adimessi30.elevatorsystem.components.abstractions.Button;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import com.adimessi30.elevatorsystem.enums.Direction;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.adimessi30.elevatorsystem.enums.Direction.DOWN;
import static com.adimessi30.elevatorsystem.enums.Direction.UP;
import static com.adimessi30.elevatorsystem.helpers.HookFactory.getHookForButtonCallback;

@Slf4j
public class ElevatorButton extends Button {
    @Setter
    @Getter
    private int mappedFloor;

    public ElevatorButton(RequestDispatcher dispatcher) {
        super(dispatcher);
    }

    public void requestFloor(int elevatorId, int currentFloor) {
        this.setPressed(true);
        createElevatorRequest(currentFloor, elevatorId, false);
    }

    public void cancelFloorRequest(int elevatorId, int currentFloor) {
        createElevatorRequest(currentFloor, elevatorId, true);
    }

    private void createElevatorRequest(int currentFloor, int elevatorId, boolean shouldCancel) {
        sendRequestToDispatcher(ElevatorRequest.builder()
                .direction(getDirection(currentFloor))
                .elevatorId(elevatorId)
                .floorNumber(mappedFloor)
                .buttonCallback(getHookForButtonCallback(this))
                .shouldCancel(shouldCancel)
                .build());
    }

    private Direction getDirection(int currentFloor) {
        return this.mappedFloor > currentFloor ? UP : DOWN;
    }

    protected void postConstruct() {
        log.debug("New elevatorButton was created.......");
    }
}
