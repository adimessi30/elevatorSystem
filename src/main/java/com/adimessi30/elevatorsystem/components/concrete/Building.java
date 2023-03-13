package com.adimessi30.elevatorsystem.components.concrete;

import com.adimessi30.elevatorsystem.helpers.CollectionFactory;
import jakarta.inject.Provider;

import java.util.List;

public class Building {
    private final List<Floor> floors;

    protected Building(int numFloors, Provider<Floor> floorProvider) {
        floors = CollectionFactory.generateCollectionFromProvider(floorProvider, numFloors,
                ((floor, floorNumber) -> {
                    if (floor.getFloorNumber() == numFloors - 1)
                        floor.setTopFloor();
                })
        );
    }

    public Floor accessFloor(int floorNumber) {
        if (floorNumber < 0 || floorNumber >= floors.size()) {
            throw new IllegalArgumentException("Floor number is out of range");
        }
        return floors.get(floorNumber);
    }

    protected void postConstruct() {
//        System.out.printf("%nNew instance of building with %d floors was created......%n", floors.size());
    }
}
