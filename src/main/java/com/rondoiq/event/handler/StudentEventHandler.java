package com.rondoiq.event.handler;

import com.rondoiq.event.event.StudentCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class StudentEventHandler {

    @EventHandler
    public void on(StudentCreatedEvent event) {
        System.out.println("Id in sink : " + event.getId());
        System.out.println("Name in sink: " + event.getName());
        System.out.println("Age in sink: " + event.getAge());
    }

}
