package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;


public class Lec05MulticastDirectBestEffort {
    public static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {
        demo02();
        Util.sleepSeconds(10);
    }
    /* Sinks.many().multicast().onBackpressureBuffer()
     * If one subscriber is slow it can effect the performance of other subscribers. Fast subscriber might not get messages
     * */
    private static void demo01(){
        System.setProperty("reactor.bufferSize.small", "16");
//        handle through which we would push items
        //onBackPressureBuffer - Bounded queue (Subscriber can join late, till then everything will store in memory - max 256).
        var sink = Sinks.many().multicast().onBackpressureBuffer();

//        handle through which subscriber will receive items
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Dolly"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Prashant"));

        for(int i=1; i<=100; i++){
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }

    /* directBestEffort - focus on the fast subscriber and ignore slow subscriber */
    private static void demo02(){
        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().directBestEffort();

        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Dolly"));
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Prashant"));

        for(int i=1; i<=100; i++){
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
