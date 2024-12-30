package com.utsavi.spring_react_demo.sec07;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehaviourDemo {
    public static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        fluxSink.next(i);
                    }
                })
                .doOnNext(v -> log.info("value: {}", v));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub1"));
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
