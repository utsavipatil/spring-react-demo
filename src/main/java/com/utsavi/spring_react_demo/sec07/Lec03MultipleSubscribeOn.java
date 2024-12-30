package com.utsavi.spring_react_demo.sec07;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/* We can have multiple subscribeOn
*  The closest to the producer/source will take precedence!
*  Always producer's scheduler is priority. Who consumers producer they always request to use suggested Scheduler but first priority will be from Producers
* */
public class Lec03MultipleSubscribeOn {
    public static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                })
                .subscribeOn(Schedulers.newParallel("Utsavi"))
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable1 = () -> flux.subscribe(Util.subscriber());

        Thread thread1 = new Thread(runnable1);
        thread1.start();

        Util.sleepSeconds(2);
    }
}
