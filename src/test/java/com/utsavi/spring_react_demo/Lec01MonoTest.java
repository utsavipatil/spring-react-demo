package com.utsavi.spring_react_demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/* Write a simple test using StepVerifier
*  StepVerifier acts like a subscriber */
public class Lec01MonoTest {
    public static final Logger log = LoggerFactory.getLogger(Lec01MonoTest.class);

    //assume a method from your service class
    private Mono<String> getProduct(int id){
        return Mono.fromSupplier(()->"product-"+id)
                .doFirst(()-> log.info("invoked"));
    }

    @Test
    public void productTest(){
        StepVerifier.create(getProduct(1))
                .expectNext("product-1")
                .expectComplete()
                .verify(); //Subscribing to publisher
    }
}
