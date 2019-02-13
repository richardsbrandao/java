package com.richard.studies.reactive.hello;

import io.reactivex.Flowable;

import java.util.concurrent.CompletableFuture;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Just \\/");
        Flowable.just("Hello World")
                .subscribe(string -> System.out.println(string));

        System.out.println("fromFuture \\/");
        Flowable.fromFuture(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("- Some IO");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello from the Future";
        })).subscribe(string -> System.out.println(string));

        System.out.println("fromArray \\/");
        Flowable.fromArray(new String[] { "Richard ", "dos ", "Santos ", "Brandao" })
                .subscribe(string -> System.out.print(string));
    }
}
