package com.utsavi.spring_react_demo.sec08;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/*
 *   Reactor automatically handles backpressure
 *   System.setProperty("reactor.bufferSize.small", "16");
 * */
public class Lec01BackPressureHandling {
    public static final Logger log = LoggerFactory.getLogger(Lec01BackPressureHandling.class);

    public static void main(String[] args) {

//        Instead of producing 256 items and wait for consumer to consume. Now will produce 16 items and wait to consume
        System.setProperty("reactor.bufferSize.small", "16"); //Max is 256 , Min 16

        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());
        producer
                .publishOn(Schedulers.boundedElastic())
                .map(Lec01BackPressureHandling::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
