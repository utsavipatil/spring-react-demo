package com.utsavi.spring_react_demo.sec10;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;


public class Lec05GroupedFlux {
    public static final Logger log = LoggerFactory.getLogger(Lec05GroupedFlux.class);

    public static void main(String[] args) {
        Flux.range(1,30)
                .delayElements(Duration.ofMillis(500))
                .map(i -> i*2)
                .startWith(1)
                .groupBy(i -> i % 2)
                //0 , 1 will be keys of fluxes
                .flatMap(Lec05GroupedFlux::processEvents)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux){
        log.info("Received flux for {}", groupedFlux.key());
        return groupedFlux.doOnNext(i -> log.info("Key: {}, Item: {}", groupedFlux.key(), i))
                .doOnComplete(()->log.info("{} Completed", groupedFlux.key()))
                .then();
    }
}
