package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/* Calls multiple publishers in a specific order */
public class Lec01StartWith {
    private static final Logger log = LoggerFactory.getLogger(Lec01StartWith.class);

    public static void main(String[] args) {

        demo05();

        Util.sleepSeconds(3);
    }

    private static void demo01(){
        producer1()
                .startWith(-1,0)
                .take(2)
                .subscribe(Util.subscriber());
    }
    private static void demo02(){
        producer1()
                .startWith(List.of(-2,-1,0))
                .subscribe(Util.subscriber());
    }
    private static void demo03(){
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }
    private static void demo04(){
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber());
    }
    private static void demo05(){
        producer1()
                .startWith(0)
                .startWith(producer2())
                .startWith(49,50)
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .doOnSubscribe(s -> log.info("Subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }
    public static Flux<Integer> producer2(){
        return Flux.just(51,52,53)
                .doOnSubscribe(s -> log.info("Subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
