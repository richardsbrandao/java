package com.richard.studies.reactive.hello;

import io.reactivex.Flowable;

public class FlowableSort {
    public static void main(String[] args) {
        Flowable.just(8, 2, 5, 4, 1)
                .toSortedList()
                .subscribe(System.out::println);

        Flowable.just(new Wrapper(8), new Wrapper(2),
                new Wrapper(5), new Wrapper(4), new Wrapper(1))
                .toSortedList()
                .subscribe(System.out::println);


    }

    public static class Wrapper implements Comparable {
        private Integer integer;

        public Wrapper(Integer integer) {
            this.integer = integer;
        }

        @Override
        public int compareTo(Object o) {
            Wrapper other = (Wrapper) o;
            return integer.compareTo(other.integer);
        }

        @Override
        public String toString() {
            return "Wrapper{" +
                    "integer=" + integer +
                    '}';
        }
    }
}
