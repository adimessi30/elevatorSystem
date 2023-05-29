package com.adimessi30.elevatorsystem.components;

import com.adimessi30.elevatorsystem.components.abstractions.Resettable;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class Building extends Resettable {
    private final List<Floor> floors;
    private final List<Elevator> elevators;
    private final RequestDispatcher requestDispatcher;

    public Floor accessFloor(int floorNumber) {
        if (floorNumber < 0 || floorNumber >= floors.size()) {
            throw new IllegalArgumentException("Floor number is out of range");
        }
        return floors.get(floorNumber);
    }

    public Elevator accessElevator(int elevatorId) {
        if (elevatorId < 0 || elevatorId >= elevators.size()) {
            throw new IllegalArgumentException("Elevator id is out of range");
        }
        return elevators.get(elevatorId);
    }

    protected void postConstruct() {
        log.debug("New instance of building with {} floors and {} elevators was created......",
                floors.size(), elevators.size());
    }

    @VisibleForTesting
    @Override
    public void reset() {
        floors.forEach(Floor::reset);
        elevators.forEach(Elevator::reset);
        requestDispatcher.reset();
    }
}
