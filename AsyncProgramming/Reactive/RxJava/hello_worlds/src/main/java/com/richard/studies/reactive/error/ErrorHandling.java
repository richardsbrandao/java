package com.richard.studies.reactive.error;

import com.richard.studies.reactive.error.exceptions.InvalidEvenNumber;
import com.richard.studies.reactive.error.exceptions.OddNumberException;
import io.reactivex.Flowable;

import java.util.Random;
import java.util.concurrent.Callable;

public class ErrorHandling {
    public static void main(String[] args) {
        // No Error Handling
//        Flowable.fromCallable(getEvenNumber())
//            .subscribe(ErrorHandling::print);


        Flowable.fromCallable(getEvenNumber())
                .doOnError(ErrorHandling::doOnError)
                .onErrorResumeNext(Flowable.just(1000))
                .subscribe(
                        ErrorHandling::print,
                        ErrorHandling::printError
                );

        Flowable.fromCallable(getEvenNumber())
                .retry(10)
                .doOnError(ErrorHandling::doOnError)
                .onErrorResumeNext(Flowable.just(1000))
                .subscribe(
                        ErrorHandling::print,
                        ErrorHandling::printError
                );
    }

    private static void doOnError(Throwable throwable) {
        if(throwable instanceof OddNumberException) {
            System.out.println("Invalid Number: " + ((OddNumberException) throwable).getNumber());
        }
        System.out.println("DoOnError: " + throwable.getMessage());
    }

    private static void printError(Throwable throwable) {
        System.out.println("Error occurred - " + throwable.getMessage());
    }

    private static void print(Integer integer) {
        System.out.println("Number is " + integer);
    }

    private static Callable<Integer> getEvenNumber() {
        return () -> {
            int number = new Random().nextInt(100);
            System.out.println("Test: " + number);
            if(number % 2 == 1) {
                throw new OddNumberException(number);
            }
            if(number < 80) {
                throw new InvalidEvenNumber(number);
            }
            return number;
        };
    }
}
