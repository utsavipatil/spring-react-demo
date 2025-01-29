package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

/* If any subscriber is slow, NO ONE will get messages - Either OR no one */
public class Lec06MulticastDirectAllOrNothing {
    public static final Logger log = LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);

    public static void main(String[] args) {
        demo01();
    }
    private static void demo01(){
        System.setProperty("reactor.bufferSize.small", "16");
        var sink = Sinks.many().multicast().directAllOrNothing();

        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Dolly"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Prashant"));

        for(int i=1; i<=100; i++){
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
