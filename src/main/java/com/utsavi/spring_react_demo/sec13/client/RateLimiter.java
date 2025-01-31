package com.utsavi.spring_react_demo.sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private static final Map<String, Integer> categoryAttempts = Collections.synchronizedMap(new HashMap<>());

    static {
        refresh();
    }

    static <T> Mono<T> limitCalls() {
        return Mono.deferContextual(contextView -> {
            var allowCall = contextView.<String>getOrEmpty("category")
                    .map(categoryString -> canAllow(categoryString))
                    .orElse(false);

            return allowCall ? Mono.empty() : Mono.error(new RuntimeException("exceed the given limit"));
        });
    }

    private static boolean canAllow(String category) {
        var attempts = categoryAttempts.getOrDefault(category, 0);
        if (attempts > 0) {
            categoryAttempts.put(category, attempts - 1);
            return true;
        }
        return false;
    }

    private static void refresh(){
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L) //When application start categoryAttempts will present
                .subscribe(i -> {
                    categoryAttempts.put("standard", 2);
                    categoryAttempts.put("prime", 3);
                });
    }
}
