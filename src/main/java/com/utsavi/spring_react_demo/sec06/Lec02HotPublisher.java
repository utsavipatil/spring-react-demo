package com.utsavi.spring_react_demo.sec06;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*  Hot - 1 data producer for all subscribers
*   share => publish().refCount(1)
*   It needs 1 min subscriber to emit data
*   It stops when there is 0 subscribers
*   Re-subscription - It starts again when there is a new Subscriber
*   To have min 2 subscribers, use publish().refCount(2);
* */
public class Lec02HotPublisher {
    public static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
//        var movieFlux = movieStream().publish().refCount(1);
        var movieFlux = movieStream().share();

        Util.sleepSeconds(2);
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(3);
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(6);
    }

    //Movie theater
    private static Flux<String> movieStream() {
        return Flux.generate(() -> {
                    log.info("Received the request");
                    return 1;
                },
                (state, sink) -> {
                    var scene = "movie scene " + state;
                    log.info("playing {}", scene);
                    sink.next(scene);
                    state++;
                    return state;
                }
        )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
