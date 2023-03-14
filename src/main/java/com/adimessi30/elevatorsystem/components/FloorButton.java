package com.adimessi30.elevatorsystem.components;

import com.adimessi30.elevatorsystem.components.abstractions.Button;
import com.adimessi30.elevatorsystem.enums.Direction;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FloorButton extends Button {

    public FloorButton(RequestDispatcher dispatcher) {
        super(dispatcher);
    }

    protected void postConstruct() {
        log.debug("New floorButton was created.......");
    }

    public void press(Direction direction, int floorNumber) {
        if (canPressButton())
            super.press(-1, floorNumber, direction, false);
    }
}
