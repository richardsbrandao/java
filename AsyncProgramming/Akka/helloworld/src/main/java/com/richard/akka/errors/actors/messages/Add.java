package com.richard.akka.errors.actors.messages;

public class Add {
    private final String value;

    public Add(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
