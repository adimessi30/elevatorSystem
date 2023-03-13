package com.adimessi30.elevatorsystem.helpers;

import com.adimessi30.elevatorsystem.components.Floor;

import java.util.function.BiConsumer;

public class HookFactory {
    public static BiConsumer<Floor, Integer> getHookForFloorCollection(int numFloors) {
        return (floor, floorNumber) -> {
            if (floor.getFloorNumber() == numFloors - 1)
                floor.setTopFloor();
        };
    }
}
