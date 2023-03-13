package com.adimessi30.elevatorsystem.components.concrete;

import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import lombok.RequiredArgsConstructor;

import java.util.Queue;

@RequiredArgsConstructor
public class RequestDispatcher {

    private final Queue<ElevatorRequest> elevatorRequests;

    protected void dispatch(ElevatorRequest elevatorRequest) {
        elevatorRequests.add(elevatorRequest);
    }

    protected void postConstruct() {
//        System.out.println("New requestDispatcher instance was created...........");
    }
}
