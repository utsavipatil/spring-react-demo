package com.utsavi.spring_react_demo;

import com.utsavi.spring_react_demo.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,50)
                .log();
    }

    private Flux<Integer> getRandomItems(){
        return Flux.range(1,50)
                .map(i -> Util.faker().random().nextInt(1,100));
    }

    @Test
    public void rangeTest1(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3)
                .expectNextCount(47) //Won't test all items . Test only count
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest2(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3)
                .expectNextCount(22) //Won't test all items . Test only count
                .expectNext(26,27,28)
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest3(){
        StepVerifier.create(getRandomItems())
                .expectNextMatches(value -> value> 0 && value < 101)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest4(){
        StepVerifier.create(getRandomItems())
                //All produced item should
                .thenConsumeWhile(value -> value> 0 && value < 101)
                .expectComplete()
                .verify();
    }
}
