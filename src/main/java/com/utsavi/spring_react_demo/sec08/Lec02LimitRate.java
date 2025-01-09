package com.utsavi.spring_react_demo.sec08;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*  Reactor automatically handles the backpressure
 *   We can also adjust the limit
 * */
public class Lec02LimitRate {
    public static final Logger log = LoggerFactory.getLogger(Lec02LimitRate.class);

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
                .limitRate(5) //Do not produce more than 5 items - Once queue 75% empty producer produce more data
                .publishOn(Schedulers.boundedElastic())
                .map(Lec02LimitRate::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        log.info("{}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
