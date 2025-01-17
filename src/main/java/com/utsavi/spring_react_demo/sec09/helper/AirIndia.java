package com.utsavi.spring_react_demo.sec09.helper;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AirIndia {
    public static final String AIRLINE = "AirIndia";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(5,10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200,1200)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(300,1200)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
