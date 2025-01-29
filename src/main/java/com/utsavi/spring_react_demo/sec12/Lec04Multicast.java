package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Lec04Multicast {
    public static final Logger log = LoggerFactory.getLogger(Lec04Multicast.class);

    public static void main(String[] args) {
        demo02();
    }
    private static void demo01(){
//        handle through which we would push items
        //onBackPressureBuffer - Bounded queue (Subscriber can join late, till then everything will store in memory - max 256).
        var sink = Sinks.many().multicast().onBackpressureBuffer();

//        handle through which subscriber will receive items
        var flux = sink.asFlux();
        flux.subscribe(Util.subscriber("Dolly"));
        flux.subscribe(Util.subscriber("Prashant"));

        sink.tryEmitNext("hi"); //Through which we can keep on sending message without sending Complete / Error message
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("I am fine");

        flux.subscribe(Util.subscriber("Parth"));
        sink.tryEmitNext("Let's play");

        Util.sleepSeconds(2);
    }

    /* WARM UP behaviour of multicast() - While emitting message if there are no subscribers then all message will go to queue.
     When first subscriber come that time all queue messages will be given to FIRST SUBSCRIBER ONLY */
    private static void demo02(){
//        handle through which we would push items
        //onBackPressureBuffer - Bounded queue (Subscriber can join late, till then everything will store in memory - max 256).
        var sink = Sinks.many().multicast().onBackpressureBuffer();

//        handle through which subscriber will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi"); //Through which we can keep on sending message without sending Complete / Error message
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("I am fine");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Dolly"));
        flux.subscribe(Util.subscriber("Prashant"));
        flux.subscribe(Util.subscriber("Parth"));
        sink.tryEmitNext("Let's play");

    }
}
