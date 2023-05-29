package com.adimessi30.elevatorsystem.helpers;

import com.adimessi30.elevatorsystem.components.Elevator;
import com.adimessi30.elevatorsystem.components.Floor;
import com.adimessi30.elevatorsystem.components.abstractions.Button;

import java.util.function.BiConsumer;

import static com.adimessi30.elevatorsystem.helpers.IterTools.forEachWithCounter;

public class HookFactory {
    public static BiConsumer<Floor, Integer> getHookForFloorCollection(int numFloors) {
        return (floor, floorNumber) -> {
            if (floor.getFloorNumber() == numFloors - 1)
                floor.disableUp();
            if (floor.getFloorNumber() == 0)
                floor.disableDown();
        };
    }

    public static Runnable getHookForButtonCallback(Button button) {
        return () -> button.setPressed(false);
    }

    public static BiConsumer<Elevator, Integer> getHookForElevatorCollection() {
        return ((elevator, index_) -> {
            forEachWithCounter(elevator.getButtons(), (buttonIndex, elevatorButton) -> {
                elevatorButton.setMappedFloor(buttonIndex);
            });
        });
    }
}
