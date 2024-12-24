package com.utsavi.spring_react_demo.sec06;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
*   -publish().autoConnect(0) will provide new values to the subscribers
*   -replay allows us to cache
* */
public class Lec04HotPublisherCache {
    public static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        var stockFlux = stockStream().replay(1).autoConnect(0);//replay(10) - last 10 value cache else it'll store everything

        Util.sleepSeconds(4);
        log.info("Same joining");
        stockFlux
                .subscribe(Util.subscriber("Sam"));

        Util.sleepSeconds(4);
        log.info("Mike joining");
        stockFlux
                .subscribe(Util.subscriber("Mike")); //When mike joins he'll get latest updated value

        Util.sleepSeconds(6);
    }

    //Stock theater
    private static Flux<Integer> stockStream() {
        return Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().random().nextInt(10,100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Emitting price: {}", price))
                .cast(Integer.class);
    }
}
