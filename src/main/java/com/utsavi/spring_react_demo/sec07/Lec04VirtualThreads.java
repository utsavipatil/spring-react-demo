package com.utsavi.spring_react_demo.sec07;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*  Reactor supports virtual streams
*   Bounded Elastic thread pool can use virtual Threads
*   System.setProperty("reactor.scheduler.defaultBoundedElasticVirtualThreads", "true");
*
*   Virtual Threads = not for CPU intensive tasks. They are time-consuming IO network calls for blocking operations.
* */
public class Lec04VirtualThreads {
    public static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    public static void main(String[] args) {

        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                })
                .subscribeOn(Schedulers.newParallel("Utsavi"))
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1-{}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable1);
        Util.sleepSeconds(2);
    }
}
