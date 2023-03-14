package com.adimessi30.elevatorsystem;

import com.adimessi30.elevatorsystem.components.Building;
import com.adimessi30.elevatorsystem.components.Elevator;
import com.adimessi30.elevatorsystem.components.Floor;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(locations = "classpath:spring-configurations/application.xml")
public class ElevatorSystemTest {

    private static final int NUM_FLOORS = 10;
    private static final int NUM_ELEVATORS = 2;

    @Autowired
    private ApplicationContext applicationContext;
    @NonNull
    @Autowired
    private RequestDispatcher requestDispatcher;
    @NonNull
    @Autowired
    private Building building;
    @NonNull
    @Autowired
    @Qualifier("elevatorRequests")
    private Queue<ElevatorRequest> elevatorRequests;
    @NonNull
    @Autowired
    @Qualifier("floors")
    private List<Floor> floors;
    @NonNull
    @Autowired
    @Qualifier("elevators")
    private List<Elevator> elevators;

    @BeforeAll
    public static void setup() {
        System.setProperty("user.property.numFloors", String.valueOf(NUM_FLOORS));
        System.setProperty("user.property.numElevators", String.valueOf(NUM_ELEVATORS));
    }

    @BeforeEach
    public void cleanUp() {
        building.reset();
    }

    @Test
    public void testCollectionBeans() {
        assertEquals(NUM_FLOORS, floors.size());
        assertEquals(NUM_ELEVATORS, elevators.size());
    }

    @Test
    public void testFloorRequestsGetDispatched() {
        building.accessFloor(2).goUp();
        building.accessFloor(3).goDown();
        building.accessFloor(0).goDown();
        building.accessFloor(NUM_FLOORS - 1).goUp();
        assertEquals(2, elevatorRequests.size());
    }

    @Test
    public void testElevatorRequestsGetDispatched() {
        building.accessElevator(0).pressButton(8);
        building.accessElevator(0).pressButton(8);
        building.accessElevator(1).pressButton(4);
        building.accessElevator(1).pressButton(0);
        building.accessElevator(0).pressButton(3);
        building.accessElevator(0).cancelButton(8);
        assertEquals(4, elevatorRequests.size());
        assertTrue(elevatorRequests.stream().anyMatch(ElevatorRequest::isCancellation));
    }

    @Test
    public void testNegativeCases() {
        Building building = applicationContext.getBean(Building.class);
        assertThrows(IllegalArgumentException.class, () -> building.accessFloor(NUM_FLOORS));
        assertThrows(IllegalArgumentException.class, () -> building.accessElevator(NUM_ELEVATORS));
        assertThrows(IllegalArgumentException.class, () -> building.accessElevator(NUM_ELEVATORS - 1)
                .pressButton(NUM_FLOORS));
    }
}
