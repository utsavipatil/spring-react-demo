package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.InputMismatchException;

/*  How to handle error in a reactive pipeline
    Flux.(....)
    ....
    ....
* */
public class Lec06ErrorHandling {
    public static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {

    }

    //Continue even if you get error
    private static void onErrorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorContinue((ex, obj) -> log.error(" ==> {}", obj, ex))
                .subscribe(Util.subscriber());
    }

    //in case of error, emit complete
    private static void onErrorComplete() {
        Mono.error(new RuntimeException("oops"))
                .onErrorComplete() //Without throwing error it will run onComplete
                .subscribe(Util.subscriber());
    }

    //When you have to use another publisher in case of error
    private static void onErrorResume() {
        /* If error comes
         * 1. If ArithmeticException => fallback1
         * 2. Else fallback2
         * 3. If fallback2 comes error then return -5 */
//        Mono.just(5)
//            .map(i -> i == 5 ? 5 / 0 : i)
        Mono.error(new InputMismatchException("oops"))
                .onErrorResume(ArithmeticException.class, ex -> fallback1())
                .onErrorResume(ex -> fallback2())
                .onErrorReturn(-5)
//            .onErrorResume(ex -> fallback())
                .subscribe(Util.subscriber());
    }

    //When you want to return a hardcoded value / simple computation
    private static void onErrorReturn() {
        //        Flux.range(1,10)
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1) //Return -1 when only IllegalArgumentException comes
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3) //in case of error return -3. So application continues running (won't crash)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker.random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.error(new IllegalArgumentException("invalid"));
    }
}
