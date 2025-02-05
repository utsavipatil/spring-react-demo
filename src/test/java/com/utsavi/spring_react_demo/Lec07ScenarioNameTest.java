package com.utsavi.spring_react_demo;

import com.utsavi.spring_react_demo.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,3)
                .log();
    }

    @Test
    public void rangeTest1(){
        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test"); //Test description
        StepVerifier.create(getItems(), options)
                .expectNext(1)
                .as("first item should be 1") //Step description
                .expectNext(2,3)
                .as("then 2 and 3")
                .expectComplete()
                .verify();
    }
}
