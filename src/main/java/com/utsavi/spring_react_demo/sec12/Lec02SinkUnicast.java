package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Sinks;

/*
*   We can emit multiple messages. But there will be only one subscriber.
* */
public class Lec02SinkUnicast {
    public static void main(String[] args) {
        demo02();
    }
    private static void demo01(){
//        handle through which we would push items
    //onBackPressureBuffer - Unbounded queue (Subscriber can join late, till then everything will store in memory).Unbounded options are there
        var sink = Sinks.many().unicast().onBackpressureBuffer();

//        handle through which subscriber will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi"); //Through which we can keep on sending message without sending Complete / Error message
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("hi");

        flux.subscribe(Util.subscriber("Sam"));
    }
    private static void demo02(){
//        handle through which we would push items
        //onBackPressureBuffer - Unbounded queue (Subscriber can join late, till then everything will store in memory).Unbounded options are there
        var sink = Sinks.many().unicast().onBackpressureBuffer();

//        handle through which subscriber will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi"); //Through which we can keep on sending message without sending Complete / Error message
        sink.tryEmitNext("How are you?");
        sink.tryEmitNext("hi");

        flux.subscribe(Util.subscriber("Sam"));
        flux.subscribe(Util.subscriber("Dolly"));
    }
}
