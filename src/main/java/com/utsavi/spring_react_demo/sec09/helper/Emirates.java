package com.utsavi.spring_react_demo.sec09.helper;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
//Represent Client class to call remote service
public class Emirates {
    public static final String AIRLINE = "Emirates";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(2,10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200,1000)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(300,1000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
