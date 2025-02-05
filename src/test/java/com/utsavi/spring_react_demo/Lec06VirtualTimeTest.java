package com.utsavi.spring_react_demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,5)
                .delayElements(Duration.ofSeconds(10));
    }

//    We cannot run
//    @Test
//    public void rangeTest1(){
//        StepVerifier.create(getItems())
//                .expectNext(1,2,3)
//                .expectComplete()
//                .verify();
//    }

    @Test
    public void virtualTimeTest1(){
        StepVerifier.withVirtualTime(() -> getItems())
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1,2,3,4,5)//Subscription object implicitly mentioned
                .expectComplete()
                .verify();
    }
    @Test
    public void virtualTimeTest2(){
        StepVerifier.withVirtualTime(() -> getItems())
                .expectSubscription() //Explicitly have to mention
                .expectNoEvent(Duration.ofSeconds(9))//Subscription is not expected
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2,3,4,5)
                .expectComplete()
                .verify();
    }
}


