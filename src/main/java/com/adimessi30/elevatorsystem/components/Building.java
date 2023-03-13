package com.adimessi30.elevatorsystem.components;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class Building {
    private final List<Floor> floors;
    private final List<Elevator> elevators;

    protected void postConstruct() {
        log.debug("New instance of building with {} floors and {} elevators was created......",
                floors.size(), elevators.size());
    }
}
