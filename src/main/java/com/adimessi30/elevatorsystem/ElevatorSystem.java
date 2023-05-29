package com.adimessi30.elevatorsystem;

import com.adimessi30.elevatorsystem.components.Building;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;
import java.util.Queue;

@Slf4j
@SpringBootApplication
public class ElevatorSystem {

    public static void main(String[] args) {
        int numberOfFloors = 10;
        int numberOfElevators = 2;
        System.setProperty("user.property.numFloors", String.valueOf(numberOfFloors));
        System.setProperty("user.property.numElevators", String.valueOf(numberOfElevators));
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-configurations/application.xml");
        context.getBean(Building.class).accessFloor(2).goUp();
        log.info(Objects.requireNonNull(context.getBean("elevatorRequests", Queue.class).poll()).toString());
    }
}
