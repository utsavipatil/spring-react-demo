package com.utsavi.spring_react_demo;

import com.utsavi.spring_react_demo.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {
    private UnaryOperator<Flux<String>> processor(){
        return flux -> flux
                .filter(string -> string.length() > 1)
                .map(String::toUpperCase)
                .map(string -> string + ":" + string.length());
    }

    @Test
    public void publisherTest1(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

//        flux.subscribe(Util.subscriber());
//        publisher.emit("hi","hello"); //emit -> Next + Complete
//        publisher.next("a","b");
//        publisher.complete();

        StepVerifier.create(flux.transform(processor()))
                //Test is subscribing to flux with passing runnable
                .then(()-> publisher.emit("hi","hello"))
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();
    }

    @Test
    public void publisherTest2(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                //Test is subscribing to flux with passing runnable
                .then(()-> publisher.emit("a","b"))
                .expectComplete()
                .verify();
    }
}
