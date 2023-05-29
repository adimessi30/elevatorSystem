package com.adimessi30.elevatorsystem.entities;

import com.adimessi30.elevatorsystem.enums.Direction;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ElevatorRequest {
    private int floorNumber;
    @NonNull
    private Direction direction;
    @NonNull
    Runnable buttonCallback;
    private int elevatorId = -1;
    private boolean cancellation;

    @Override
    public String toString() {
        return "ElevatorRequest{" +
                "floorNumber=" + floorNumber +
                ", direction=" + direction +
                ", buttonCallback=" + buttonCallback +
                ", elevatorId=" + elevatorId +
                ", isCancellation=" + cancellation +
                '}';
    }
}
