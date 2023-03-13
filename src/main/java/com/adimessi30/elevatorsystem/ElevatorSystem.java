package com.adimessi30.elevatorsystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class ElevatorSystem {

    public static void main(String[] args) {
        int numberOfFloors = 10;
        int numberOfElevators = 2;
        System.setProperty("user.property.numFloors", String.valueOf(numberOfFloors));
        System.setProperty("user.property.numElevators", String.valueOf(numberOfElevators));
        ApplicationContext context = new ClassPathXmlApplicationContext("application-config.xml");
    }
}
