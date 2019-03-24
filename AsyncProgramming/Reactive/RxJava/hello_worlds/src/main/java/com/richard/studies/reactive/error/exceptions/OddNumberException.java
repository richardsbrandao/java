package com.richard.studies.reactive.error.exceptions;

public class OddNumberException extends RuntimeException {
    private final int number;

    public OddNumberException(int number) {
        super("Wrong number: " + number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
