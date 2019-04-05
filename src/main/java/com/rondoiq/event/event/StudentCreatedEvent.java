package com.rondoiq.event.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentCreatedEvent {

    private final String id;
    private final String name;
    private final String age;

    @JsonCreator
    public StudentCreatedEvent(@JsonProperty("id") String id
            , @JsonProperty("name") String name
            , @JsonProperty("age") String age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
}
