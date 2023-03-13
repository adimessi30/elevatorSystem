package com.adimessi30.elevatorsystem.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class Building {
    private final List<Floor> floors;
    private final List<Elevator> elevators;

    public Floor accessFloor(int floorNumber) {
        if (floorNumber < 0 || floorNumber >= floors.size()) {
            throw new IllegalArgumentException("Floor number is out of range");
        }
        return floors.get(floorNumber);
    }

    protected void postConstruct() {
        log.debug("New instance of building with {} floors and {} elevators was created......",
                floors.size(), elevators.size());
    }
}
