package com.adimessi30.elevatorsystem;

import com.adimessi30.elevatorsystem.components.Building;
import com.adimessi30.elevatorsystem.components.Elevator;
import com.adimessi30.elevatorsystem.components.Floor;
import com.adimessi30.elevatorsystem.entities.ElevatorRequest;
import com.adimessi30.elevatorsystem.managers.RequestDispatcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ContextConfiguration(locations = "classpath:spring-configurations/application.xml")
public class ElevatorSystemTest {

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    public static void setup() {
        System.setProperty("user.property.numFloors", "10");
        System.setProperty("user.property.numElevators", "2");
    }

    @Test
    public void testPrimaryComponentsAreLoaded() {
        RequestDispatcher requestDispatcher = applicationContext.getBean(RequestDispatcher.class);
        assertNotNull(requestDispatcher);
        Building building = applicationContext.getBean(Building.class);
        assertNotNull(building);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCollectionBeans() {
        Queue<ElevatorRequest> elevatorRequests = applicationContext.getBean("elevatorRequests", Queue.class);
        assertNotNull(elevatorRequests);
        List<Floor> floors = applicationContext.getBean("floors", List.class);
        assertEquals(10, floors.size());
        List<Elevator> elevators = applicationContext.getBean("elevators", List.class);
        assertEquals(2, elevators.size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testFloorRequestsGetDispatched() {
        Building building = applicationContext.getBean(Building.class);
        building.accessFloor(2).goUp();
        building.accessFloor(3).goDown();
        building.accessFloor(0).goDown();
        building.accessFloor(9).goUp();
        Queue<ElevatorRequest> elevatorRequests = applicationContext.getBean("elevatorRequests", Queue.class);
        assertEquals(2, elevatorRequests.size());
    }

    @Test
    public void testNegativeCases() {
        Building building = applicationContext.getBean(Building.class);
        assertThrows(IllegalArgumentException.class, () -> building.accessFloor(10));
    }
}
