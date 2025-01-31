package com.utsavi.spring_react_demo.sec13;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Lec03ContextPropagation {
    public static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {
        getWelcomeMessage()
//                .concatWith(producer1())
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(context -> Context.empty())))
                .contextWrite(Context.of("user", "Dolly"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(contextView -> {
            if (contextView.hasKey("user")) {
                return Mono.just("Welcome %s !!!".formatted(contextView.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.<String>deferContextual(contextView -> {
                    log.info("producer1: {}", contextView);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2(){
        return Mono.<String>deferContextual(contextView -> {
            log.info("producer2: {}", contextView);
            return Mono.empty();
        })
                .subscribeOn(Schedulers.parallel());
    }
}
