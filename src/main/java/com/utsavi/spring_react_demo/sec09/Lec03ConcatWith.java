package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/* Once producer1 completed then only concatenate values (opposite to startWith)   */
public class Lec03ConcatWith {
    public static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        demo03();
        Util.sleepSeconds(3);
    }
    private static void demo01(){
        producer1()
                .concatWithValues(-1,0)
                .subscribe(Util.subscriber());
    }
    private static void demo02(){
        producer1()
                .concatWith(producer2()) //first producer 1 and then producer2 will run
                .subscribe(Util.subscriber());
    }
    private static void demo03(){
        Flux.concat(producer1(), producer2()).subscribe(Util.subscriber()); //First producer 1 then 2 run
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
