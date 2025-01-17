package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
*   Zip
*   - we will subscribe to all the producers at the same line
*   - all or nothing
*   - all producers will have to emit an item
* */
public class Lec07Zip {

    record Car(String body, String Engine, String tires){}

    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Flux<String> getBody(){
        return Flux.range(1,5)
                .map(i -> "Body-" + i)
                .delayElements(Duration.ofMillis(100));
    }
    private static Flux<String> getEngine(){
        return Flux.range(1,3)
                .map(i -> "Engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }
    private static Flux<String> getTires(){
        return Flux.range(1,10)
                .map(i -> "Tires-" + i)
                .delayElements(Duration.ofMillis(75));
    }
    private static Flux<Integer> producer4(){
        return Flux.range(1,10);
    }
}
