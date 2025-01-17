package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
/* If any producer throw error but consumer need data then first all producer will send data then error will throw */
public class Lec04ConcatError {
    public static final Logger log = LoggerFactory.getLogger(Lec04ConcatError.class);

    public static void main(String[] args) {
        demo01();
        Util.sleepSeconds(3);
    }
    private static void demo01(){
        producer1()
                .concatWith(producer3())
                .concatWith(producer2())
                .subscribe(Util.subscriber());
    }
    private static void demo02() {
        Flux.concatDelayError(producer1(),producer3(),producer2()).subscribe(Util.subscriber());
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
    public static Flux<Integer> producer3(){
        return Flux.error(new RuntimeException("Opps...."));
    }
}
