package com.utsavi.spring_react_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {
    Mono<String> getUsername(int userID){
        return switch (userID){
          case 1 -> Mono.just("Utsavi");
          case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    @Test
    public void productTest(){
        StepVerifier.create(getUsername(1))
                .expectNext("Utsavi")
                .expectComplete()
                .verify(); //Subscribing to publisher
    }

    @Test
    public void emptyTest(){
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest1(){
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorTest2(){
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void errorTest3(){
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("invalid input")
                .verify();
    }

    @Test
    public void errorTest4(){
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(exception -> {
                    Assertions.assertEquals(RuntimeException.class, exception.getClass());
                    Assertions.assertEquals("invalid input", exception.getMessage());
                })
                .verify();
    }
}
