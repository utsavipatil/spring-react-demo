package com.utsavi.spring_react_demo.sec09.helper;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qatar {
    public static final String AIRLINE = "Qatar";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(3,5))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(300,800)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(400,900)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
