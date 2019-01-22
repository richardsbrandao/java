package com.richard.akka.errors.actors.messages;

public class Minus {
    private final String value;

    public Minus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
