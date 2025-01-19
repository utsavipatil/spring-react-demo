package com.utsavi.spring_react_demo.sec11;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *   Retry operator simply resubscribes when it sees error signal -> once error gone it will take message only 1 time
 * */
public class Lec02Retry {
    public static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        demo02();
        Util.sleepSeconds(5);
    }

    private static void demo01() {
        getCountryNam().retry(2).subscribe(Util.subscriber());
    }

    private static void demo02() {
        getCountryNam()
//                .retryWhen(Retry.indefinitely())
//                .retryWhen(Retry.max(8))
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1))
//                        .doBeforeRetry(rs -> log.info("Retrying"))
//                        .filter(ex -> IllegalArgumentException.class.equals(ex.getClass())) //retry for illegalArguments
                                .filter(ex -> RuntimeException.class.equals(ex.getClass()))
                                .onRetryExhaustedThrow((spec, signal)->signal.failure()) //will send original error
                )
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryNam() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if (atomicInteger.incrementAndGet() < 5) { //to pass 3
                throw new RuntimeException("oops");
            }
            return Util.faker().country().name();
        }).doOnError(error -> log.error("Error {}", error.getMessage())).doOnSubscribe(s -> log.info("Subscribing"));
    }
}
