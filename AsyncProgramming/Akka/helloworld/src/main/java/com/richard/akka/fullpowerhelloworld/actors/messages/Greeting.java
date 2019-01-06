package com.richard.akka.fullpowerhelloworld.actors.messages;

public class Greeting {
    private final String whoGreet;
    private final String greeted;

    public Greeting(String whoGreet, String greeted) {
        this.whoGreet = whoGreet;
        this.greeted = greeted;
    }

    public String getWhoGreet() {
        return whoGreet;
    }

    public String getGreeted() {
        return greeted;
    }
}
