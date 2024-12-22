package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

/* Take is similar to Java stream's limit */
public class Lec05TakeOperator {
    public static void main(String[] args) {
        takeWhile();
        takeUntil();
    }
    private static void take(){
        IntStream.rangeClosed(1,10)
                .limit(3)
                .forEach(System.out::println);
        Flux.range(1,10)
                .log("take")
                .take(5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile(){
        Flux.range(1,10)
                .log("take")
                .takeWhile(i -> i < 5) //Stop when condition is not met
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil(){
        Flux.range(1,10)
                .log("take")
                .takeUntil(i -> i > 5) //Stop when condition is met + allow last item
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
