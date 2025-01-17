package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/* Subscriber subscribe to all producers at same time - producing data order is random - first come first in */
public class Lec05Merge {
    public static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {
        demo02();
        Util.sleepSeconds(3);
    }
    private static void demo01(){
        Flux.merge(producer1(),producer3(),producer2())
                .take(3)
                .subscribe(Util.subscriber());
    }
    private static void demo02(){
        producer2().mergeWith(producer1())
                .mergeWith(producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }
    public static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .transform(Util.fluxLogger("producer1"))
                .delayElements(Duration.ofMillis(10));
    }
    public static Flux<Integer> producer2(){
        return Flux.just(51,52,53)
                .transform(Util.fluxLogger("producer2"))
                .delayElements(Duration.ofMillis(10));
    }
    public static Flux<Integer> producer3(){
        return Flux.just(11,12,13)
                .transform(Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }
}
