package com.richard.studies.reactive.tourismyapp.models;

import java.util.Optional;

public class Range {
    private final Optional<Integer> min;
    private final Optional<Integer> max;

    public Range(Optional<Integer> min, Optional<Integer> max) {
        this.min = min;
        this.max = max;
    }

    public boolean in(Integer value) {
        return value >= min.orElse(Integer.MIN_VALUE) && value <= max.orElse(Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        return String.format("from: %s to %s", min, max);
    }
}
