package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*  Timeout - if during time we don't get anything(object, onComplete, onError) from producer then fallback will run else error
 *   Whenever require then only fallback will run
 *
 *   Timeout - will produce timeout error
 *       - we can handle as part of onError methods
 *   there is also an overloaded method to accept a publisher
 *   we can have multiple timeouts. The closest one to the subscriber will take effect for the subscriber
 *   With multiple timeouts we can only reduce time, but we can not increase (shorter time is already sending response)
 * */
public class Lec09Timeout {
    public static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);

    public static void main(String[] args) {
        var mono = getProductName()
                .timeout(Duration.ofSeconds(2), fallback());

//                .onErrorReturn("fallback")
        mono
                .timeout(Duration.ofMillis(200))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofSeconds(300))
                .doFirst(() -> log.info("do first"));
    }
}
