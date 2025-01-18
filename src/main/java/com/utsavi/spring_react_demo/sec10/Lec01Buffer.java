package com.utsavi.spring_react_demo.sec10;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/* Collect item based on given internal / size */
public class Lec01Buffer {
    public static final Logger log = LoggerFactory.getLogger(Lec01Buffer.class);

    public static void main(String[] args) {
        demo04();
        Util.sleepSeconds(5);
    }
    private static void demo01(){
        eventStream()
                .buffer()
        //By default, buffer will wait, int-max value or the source has to complete
                .subscribe(Util.subscriber());
    }
    private static void demo02(){
        eventStream()
                .buffer(3)
                //every 3 items
                .subscribe(Util.subscriber());
    }
    private static void demo03(){
        eventStream()
                .buffer(Duration.ofMillis(500))
                //every 500ms
                .subscribe(Util.subscriber());
    }
    private static void demo04(){
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1))
                //every 3 items but max will wait till 1 sec
                .subscribe(Util.subscriber());
    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never()) //never ending Flux - For testing purpose
                .map(i -> "event-" + (i + 1));
    }
}
