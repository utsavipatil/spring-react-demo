package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/*
*   "then" could be helpful when we are not interested in result of a publisher
*   we need to have sequential execution of asynchronous task
* */
public class Lec15Then {
    public static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {
        var records = List.of("a","b","c");
        saveRecords(records)
                .then(sendNotification(records))
//                Will simply return if result successful or not rest all loggers won't show
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
    private static Flux<String> saveRecords(List<String> records){
        return Flux.fromIterable(records)
                .map(r -> "saved" + r)
                .delayElements(Duration.ofMillis(500));
    }
    private static Mono<Void> sendNotification(List<String> records){
        return Mono.fromRunnable(()-> log.info("all {} records saved successfully", records));
    }
}
