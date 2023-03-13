package com.adimessi30.elevatorsystem.entities;

import com.adimessi30.elevatorsystem.enums.Direction;
import lombok.Builder;
import lombok.NonNull;

@Builder
public class ElevatorRequest {
    private int floorNumber;
    @NonNull
    private Direction direction;
    @NonNull
    Runnable buttonCallback;
    private int elevatorId = -1;
    private boolean shouldCancel;

    @Override
    public String toString() {
        return "ElevatorRequest{" +
                "floorNumber=" + floorNumber +
                ", direction=" + direction +
                ", buttonCallback=" + buttonCallback +
                ", elevatorId=" + elevatorId +
                ", shouldCancel=" + shouldCancel +
                '}';
    }
}
