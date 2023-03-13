package com.adimessi30.elevatorsystem.managers;

import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
@RequiredArgsConstructor
public class RequestDispatcher {

    private final Queue<ElevatorRequest> elevatorRequests;

    public void dispatch(ElevatorRequest elevatorRequest) {
        elevatorRequests.add(elevatorRequest);
    }

    protected void postConstruct() {
        log.debug("New requestDispatcher instance was created...........");
    }
}
