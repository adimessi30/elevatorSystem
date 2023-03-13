package com.adimessi30.elevatorsystem.components.concrete;

import com.adimessi30.elevatorsystem.components.abstractions.IdAutoGenerated;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Button extends IdAutoGenerated {
    private final RequestDispatcher dispatcher;

    protected void callForElevator(ElevatorRequest elevatorRequest) {
        dispatcher.dispatch(elevatorRequest);
    }

    protected void postConstruct() {
//        System.out.printf("%nNew externalButton with instance id: %d was created.......", getId());
    }
}
