package com.utsavi.spring_react_demo.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {
    public static Flux<Flight> getFlights(){
        return Flux.merge(AirIndia.getFlights(), Emirates.getFlights(), Qatar.getFlights())
                .take(Duration.ofSeconds(2));
    }
}
