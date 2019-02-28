package com.richard.studies.reactive.hello;

import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;

public class FlowableOperators {
    public static void main(String[] args) {
        Flowable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                    .groupBy((value) -> value % 2)
                    .map((groupedFlowable) -> new NumberGroup(groupedFlowable.getKey(), groupedFlowable.toList()))
                    .subscribe((numbersGroup) -> numbersGroup
                                                    .numbersInGroup
                                                    .subscribe(numbers -> System.out.println(numbersGroup.group + " - " + numbers)));
    }

    private static class NumberGroup {
        private final Integer group;
        private final Single<List<Integer>> numbersInGroup;

        public NumberGroup(Integer group, Single<List<Integer>> numbersInGroup) {
            this.group = group;
            this.numbersInGroup = numbersInGroup;
        }
    }
}
