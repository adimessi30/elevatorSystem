package com.adimessi30.elevatorsystem.entities;

import com.adimessi30.elevatorsystem.enums.Direction;
import org.springframework.lang.NonNull;

public record ElevatorRequest(int floorNumber, @NonNull Direction direction) {
    @Override
    public String toString() {
        return "ElevatorRequest{" +
                "floorNumber=" + floorNumber +
                ", direction=" + direction +
                '}';
    }
}
