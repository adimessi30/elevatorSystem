package com.adimessi30.elevatorsystem.helpers;

import com.adimessi30.elevatorsystem.components.concrete.Elevator;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.adimessi30.elevatorsystem.enums.Direction.IDLE;

public class SelectionStrategyHelper {

    public static Elevator selectElevator(List<Elevator> elevators, ElevatorRequest elevatorRequest) {
        Elevator bestElevator = null;
        int bestDistance = Integer.MAX_VALUE;
        for (Elevator elevator : elevators) {
            if (IDLE.equals(elevator.getCurrentDirection()) ||
                    elevator.getCurrentDirection().equals(elevatorRequest.direction())) {
                int distance = Math.abs(elevator.getCurrentFloor() - elevatorRequest.floorNumber());
                if (distance < bestDistance) {
                    bestElevator = elevator;
                    bestDistance = distance;
                }
            }
        }

        if (bestElevator == null) {
            for (Elevator elevator : elevators) {
                if (IDLE.equals(elevator.getCurrentDirection())) {
                    int distance = Math.abs(elevator.getCurrentFloor() - elevatorRequest.floorNumber());
                    if (distance < bestDistance) {
                        bestElevator = elevator;
                        bestDistance = distance;
                    }
                }
            }
        }

        if (bestElevator == null) {
            bestElevator = elevators.get(ThreadLocalRandom.current().nextInt(elevators.size()));
        }

        return bestElevator;
    }
}
