package com.utsavi.spring_react_demo.sec08;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*  Once 16 items consumed by consumer. Producer keep on producing data realise consumer is slow. Then every produce data goes to a separate internal queue
    Which is not giving to subscriber */
public class Lec04FluxCreate {
    public static final Logger log = LoggerFactory.getLogger(Lec04FluxCreate.class);

    public static void main(String[] args) {
        //Instead of producing 256 items and wait for consumer to consume. Now will produce 16 items and wait to consume
        System.setProperty("reactor.bufferSize.small", "16"); //Max is 256 , Min 16

        //Producer speed 20 items per second
        var producer = Flux.create(sink -> {
                    for(int i=1; i<=500 && !sink.isCancelled() ; i++){
                        log.info("generating {}", i);
                        sink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    sink.complete();
                }).cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        //Subscriber speed 1 item per second
        producer
                .limitRate(1) //Still face backpressure
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreate::timeConsumingTask)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        log.info("received: {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
