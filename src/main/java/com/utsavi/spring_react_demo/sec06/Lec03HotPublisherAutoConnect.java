package com.utsavi.spring_react_demo.sec06;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
*   almost same as publish.refCount(1)
*   - does not stop when subscribers cancel. So it will start producing even for 0 subscribers once it started
*   - make it real hot - publish().autoConnect(0)
* */
public class Lec03HotPublisherAutoConnect {
    public static final Logger log = LoggerFactory.getLogger(Lec03HotPublisherAutoConnect.class);

    public static void main(String[] args) {
        var movieFlux = movieStream().publish().autoConnect(0); //default subscribers 1

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
