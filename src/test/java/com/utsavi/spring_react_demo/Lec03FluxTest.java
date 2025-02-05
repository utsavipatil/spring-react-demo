package com.utsavi.spring_react_demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {
    private Flux<Integer> getItems(){
        return Flux.just(1,2,3)
                .log();
    }

    @Test
    public void fluxTest1(){
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()//Cancel test - producer is able to produce more, but we are not asking
                .verify();
//                .verifyComplete(); //we can not use this directly. Because test is not Ended
    }

    @Test
    public void fluxTest2(){
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .thenCancel()//Cancel test - producer is able to produce more, but we are not asking
                .verify();
    }

    @Test
    public void fluxTest3(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3)
                .thenCancel()//Cancel test - producer is able to produce more, but we are not asking
                .verify();
    }

}
