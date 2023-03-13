package com.adimessi30.elevatorsystem.components.concrete;

import com.adimessi30.elevatorsystem.components.abstractions.HasBackgroundRunnable;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.adimessi30.elevatorsystem.enums.Direction.DOWN;
import static com.adimessi30.elevatorsystem.enums.Direction.IDLE;
import static com.adimessi30.elevatorsystem.enums.Direction.UP;

public class ElevatorRequestProcessor extends HasBackgroundRunnable {
    private final List<Elevator> elevators;
    private final ElevatorRequestQueueManager queueManager;

    protected ElevatorRequestProcessor(List<Elevator> elevators, ElevatorRequestQueueManager queueManager) {
        this.elevators = elevators;
        this.queueManager = queueManager;
    }

    @SneakyThrows
    @Override
    protected void run() {
        elevators.stream().parallel()
                .forEach(elevator -> {
                    if (queueManager.hasNoRequests(elevator.getElevatorId())) {
                        System.out.printf("No more requests to process for elevator: %d", elevator.getElevatorId());
                        if (!IDLE.equals(elevator.getCurrentDirection()))
                            elevator.stop();
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        Optional.ofNullable(queueManager.getRequestToProcess(elevator.getElevatorId(),
                                        elevator.getCurrentDirection()))
                                .ifPresentOrElse(elevator::move, () -> changeDirection(elevator));
                    }
                });
    }

    private void changeDirection(Elevator elevator) {
        elevator.setCurrentDirection(UP.equals(elevator.getCurrentDirection()) ? DOWN : UP);
        queueManager.fillFromPending(elevator.getElevatorId(), elevator.getCurrentDirection());
    }

    @Override
    protected void init() {
//        System.out.println("New elevatorRequestProcessor instance was created.......");
        super.init();
    }
}
