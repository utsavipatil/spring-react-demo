package com.utsavi.spring_react_demo.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {
        var mono = Mono.just(1)
                .map(i -> i / 0);
        /* Consumer is similar like onNext method . If we pass Consumer Reactive team is requesting on behalf of us for Consumer behaviour
        * If we want manually that's also possible */
        mono.subscribe(
                i -> log.info("Received: {}", i),
                error -> log.error("Error ", error),
                () -> log.info("Completed"),
                subscription -> subscription.request(1)
        );
    }

}
