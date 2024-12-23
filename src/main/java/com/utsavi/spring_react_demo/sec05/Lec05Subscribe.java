package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {
    public static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);
    public static void main(String[] args) {
        Flux.range(1,10)
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(error -> log.error("error came ", error))
                .subscribe();
        //It just requests Long.MAX times. Instead of mentioning everything in subscriber class we are explicitly writing here
    }
}
