package com.utsavi.spring_react_demo.sec08;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/*  Reactor provides backpressure handling strategies
    -buffer
    -drop
    -latest
    -error
* */
public class Lec05BackPressureStrategies {
    public static final Logger log = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {

        //Producer speed 20 items per second
        var producer = Flux.create(sink -> {
            for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                log.info("generating {}", i);
                sink.next(i);
                Util.sleep(Duration.ofMillis(50));
            }
            sink.complete();
        }).cast(Integer.class);
//                .subscribeOn(Schedulers.parallel());

        //Subscriber speed 1 item per second
        producer
//                .onBackpressureBuffer() //Subscriber get items 1 per second from this buffer
                .onBackpressureError() //Once Producer produce more item than requested (1 limitRate) It will throw error
                //1 receives, 2-11 stored in buffer beyond 11 will throw error
                .onBackpressureBuffer(10) //Beyond 10 items in buffer It will throw error. Cancel producer and send error to downstream

                //Till subscriber consumes data whatever producer have produce will drop
                .onBackpressureDrop()

                //It will keep on dropping data until next new data. If request comes latest data will be given
                .onBackpressureLatest()
                .log()
                .limitRate(1) //Still face backpressure
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackPressureStrategies::timeConsumingTask)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        log.info("received: {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
