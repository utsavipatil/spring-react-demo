package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/* delayElements keeps on requesting every second give one value then only producer will produce */
public class Lec04Delay {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(11);
    }
}
