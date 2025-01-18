package com.utsavi.spring_react_demo.sec10;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {
    public static void main(String[] args) {
        eventStream()
//                .window(5)
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> processEvents(flux))
                .subscribe();

        Util.sleepSeconds(5);
    }
    private static Mono<Void> processEvents(Flux<String> flux){
        return flux
                .doOnNext(message -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();

    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }
}
