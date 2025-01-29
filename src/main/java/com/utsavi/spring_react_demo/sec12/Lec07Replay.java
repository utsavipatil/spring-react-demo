package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

/* replay().all(), replay.limit(1) = Multiple Subscriber can get messages. Late subscriber can see past messages */
public class Lec07Replay {
    public static final Logger log = LoggerFactory.getLogger(Lec07Replay.class);

    public static void main(String[] args) {
       demo01();
    }
    private static void demo01(){
//        handle through which we would push items
        //onBackPressureBuffer - Bounded queue (Subscriber can join late, till then everything will store in memory - max 256).
//        var sink = Sinks.many().replay().all();
        var sink = Sinks.many().replay().limit(1); //Late subscriber will see only 1 message

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
}
