package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/*
*   Sinks.one() -> We can emit multiple items via sinks -> Mono Sink
*   Sinks -> HOT publisher
*   tryEmitValue -> will try to emit the value but in case of issue it won't notify us, Try if possible otherwise ignore
*   emitValue -> we want to be sure that we will emit some value that's why we provide some kind of handler. So that you can take some actions
* */
public class Lec01SinksOne {
    public static final Logger log = LoggerFactory.getLogger(Lec01SinksOne.class);
    public static void main(String[] args) {
        demo03();
    }

    /* Exploring sink methods to emit item / empty / error */
    private static void demo01(){
        Sinks.One<Object> sink = Sinks.one(); //we can emit data
        Mono<Object> mono = sink.asMono(); //same sink can act like mono
        mono.subscribe(Util.subscriber()); //Subscribe to the Mono to consume the values emitted by the Sink
//        sink.tryEmitValue("hi");
        sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("oops"));
    }

    /* We can have multiple Subscribers */
    private static void demo02(){
        Sinks.One<Object> sink = Sinks.one(); //we can emit data
        Mono<Object> mono = sink.asMono(); //same sink can act like mono
        sink.tryEmitValue("hi"); //While emit event we don't have subscriber it will work fine
        mono.subscribe(Util.subscriber("Sam")); //Subscribe to the Mono to consume the values emitted by the Sink
        mono.subscribe(Util.subscriber("Mike"));
    }

    private static void demo03(){
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("Sam"));

//        It is able to send successfully message that's why signalType & emitResult did not run -> Its Mono
        sink.emitValue("hi",((signalType, emitResult)->{
            log.info("hi");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));

        sink.emitValue("hello",((signalType, emitResult)->{ //emitResult -> in case of issues this will help, to make retry
            log.info("hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false; //do we need Retry ??
        }));

    }
}
