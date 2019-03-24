package error;

import io.reactivex.Flowable;

import java.util.Random;
import java.util.concurrent.Callable;

public class ErrorHandling {
    public static void main(String[] args) {
        // No Error Handling
//        Flowable.fromCallable(getEvenNumber())
//            .subscribe(ErrorHandling::print);

        Flowable.fromCallable(getEvenNumber())
                .onErrorResumeNext(error -> System.out.println("On Error resume next"))
                .subscribe(ErrorHandling::print);
    }

    private static void print(Integer integer) {
        System.out.println("Number is " + integer);
    }

    private static Callable<Integer> getEvenNumber() {
        return () -> {
            int number = new Random().nextInt(100);
            if(number % 2 == 1) {
                throw new Exception("Wrong number: " + number);
            }
            return number;
        };
    }
}
