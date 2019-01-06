package com.richard.akka.fullpowerhelloworld.actors.messages;

public class Meeting {
    private String personOne;
    private String personTwo;

    public Meeting(String personOne, String personTwo) {
        this.personOne = personOne;
        this.personTwo = personTwo;
    }

    public String getPersonOne() {
        return personOne;
    }

    public String getPersonTwo() {
        return personTwo;
    }
}
