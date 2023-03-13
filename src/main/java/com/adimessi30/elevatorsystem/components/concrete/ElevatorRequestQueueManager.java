package com.adimessi30.elevatorsystem.components.concrete;

import com.adimessi30.elevatorsystem.enums.Direction;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.adimessi30.elevatorsystem.enums.Direction.UP;

public class ElevatorRequestQueueManager {

    private final Map<Integer, PriorityQueue<ElevatorRequest>> upQueueMap = new ConcurrentHashMap<>();
    private final Map<Integer, PriorityQueue<ElevatorRequest>> downQueueMap = new ConcurrentHashMap<>();
    private final Map<Integer, ConcurrentLinkedQueue<ElevatorRequest>> pendingQueueMap = new ConcurrentHashMap<>();

    protected synchronized void addRequestToPriorityQueue(ElevatorRequest elevatorRequest, int elevatorId) {
        Map<Integer, PriorityQueue<ElevatorRequest>> priorityQueueMap = getMap(elevatorRequest.direction());
        priorityQueueMap.computeIfAbsent(elevatorId,
                        k -> (UP.equals(elevatorRequest.direction())) ? getUpQueue() : getDownQueue())
                .add(elevatorRequest);
    }

    protected synchronized void addRequestToPendingQueue(ElevatorRequest elevatorRequest, int elevatorId) {
        pendingQueueMap.computeIfAbsent(elevatorId, k -> new ConcurrentLinkedQueue<>()).add(elevatorRequest);
    }

    protected synchronized void fillFromPending(int elevatorId, Direction direction) {
        if (isEmpty(pendingQueueMap, elevatorId))
            return;
        Queue<ElevatorRequest> pendingQueue = pendingQueueMap.get(elevatorId);
        while (!pendingQueue.isEmpty()) {
            addRequestToPriorityQueue(pendingQueue.poll(), elevatorId);
        }
    }

    protected synchronized ElevatorRequest getRequestToProcess(int elevatorId, Direction direction) {
        return Optional.ofNullable(getMap(direction).get(elevatorId))
                .map(PriorityQueue::poll)
                .orElse(null);
    }

    protected synchronized Map<Integer, PriorityQueue<ElevatorRequest>> getMap(Direction direction) {
        return (UP.equals(direction)) ? upQueueMap : downQueueMap;
    }

    protected synchronized boolean hasNoRequests(int elevatorId) {
        return isEmpty(upQueueMap, elevatorId) && isEmpty(downQueueMap, elevatorId)
                && isEmpty(pendingQueueMap, elevatorId);
    }

    protected synchronized boolean isEmptyInDirection(int elevatorId, Direction direction) {
        return isEmpty(getMap(direction), elevatorId);
    }

    private static <T extends Queue<?>> boolean isEmpty(Map<Integer, T> mapToCheck, int id) {
        return Optional.ofNullable(mapToCheck.get(id))
                .map(Queue::isEmpty)
                .orElse(true);
    }

    private static PriorityQueue<ElevatorRequest> getUpQueue() {
        return new PriorityQueue<>(Comparator.comparingInt(ElevatorRequest::floorNumber));
    }

    private static PriorityQueue<ElevatorRequest> getDownQueue() {
        return new PriorityQueue<>((a, b) -> (b.floorNumber() - a.floorNumber()));
    }

    protected void postConstruct() {
//        System.out.println("New queueManager instance was created.....");
    }
}
