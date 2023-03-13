package com.adimessi30.elevatorsystem.components.concrete;

import com.adimessi30.elevatorsystem.components.abstractions.HasBackgroundRunnable;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;

import java.util.List;
import java.util.Queue;

import static com.adimessi30.elevatorsystem.helpers.SelectionStrategyHelper.selectElevator;
import static com.adimessi30.elevatorsystem.enums.Direction.DOWN;
import static com.adimessi30.elevatorsystem.enums.Direction.UP;

public class ElevatorRequestConsumer extends HasBackgroundRunnable {
    private final List<Elevator> elevators;
    private final Queue<ElevatorRequest> elevatorRequests;
    private final ElevatorRequestQueueManager queueManager;

    protected ElevatorRequestConsumer(List<Elevator> elevators, Queue<ElevatorRequest> elevatorRequests,
                                      ElevatorRequestQueueManager queueManager) {
        this.elevators = elevators;
        this.elevatorRequests = elevatorRequests;
        this.queueManager = queueManager;
    }

    @Override
    protected void run() {
        ElevatorRequest elevatorRequest = elevatorRequests.poll();
        if (elevatorRequest == null)
            return;
        Elevator selectedElevator = selectElevator(elevators, elevatorRequest);
        int selectedElevatorId = selectedElevator.getElevatorId();
        if (isInDirection(elevatorRequest, selectedElevator)) {
            queueManager.addRequestToPriorityQueue(elevatorRequest, selectedElevatorId);
        } else {
            queueManager.addRequestToPendingQueue(elevatorRequest, selectedElevatorId);
        }
    }

    private static boolean isInDirection(ElevatorRequest request, Elevator elevator) {
        return (UP.equals(request.direction()) && request.floorNumber() >= elevator.getCurrentFloor()) ||
                (DOWN.equals(request.direction()) && request.floorNumber() <= elevator.getCurrentFloor());
    }

    @Override
    protected void init() {
//        System.out.println("New elevatorRequestConsumer instance was created.......");
        super.init();
    }
}
