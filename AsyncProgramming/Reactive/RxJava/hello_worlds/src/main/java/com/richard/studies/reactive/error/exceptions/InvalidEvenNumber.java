package com.richard.studies.reactive.error.exceptions;

public class InvalidEvenNumber extends RuntimeException {
    private final int number;

    public InvalidEvenNumber(int number) {
        super("Invalid Even Number: " + number);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
