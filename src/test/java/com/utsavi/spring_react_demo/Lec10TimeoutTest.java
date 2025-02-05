package com.utsavi.spring_react_demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec10TimeoutTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,5)
                .delayElements(Duration.ofMillis(200));
    }

    @Test
    public void virtualTimeTest1(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5)//Subscription object implicitly mentioned
                .expectComplete()
                .verify(Duration.ofMillis(1500)); //Whole Test should complete within given timeframe
    }
}
