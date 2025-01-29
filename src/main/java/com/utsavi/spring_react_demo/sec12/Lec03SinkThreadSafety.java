package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
/*
*   Sink is Thread safe. But internally it's not synchronized. It will fail fast if it sees some kind of multiple threads are trying to access this
*   Fix is developer's responsibility to fix this using emit failure handler.
* */
public class Lec03SinkThreadSafety {
    public static final Logger log = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);
    public static void main(String[] args) {
        demo02();
    }
    private static void demo01(){
//        handle through which we would push items
        //onBackPressureBuffer - Unbounded queue (Subscriber can join late, till then everything will store in memory).Unbounded options are there
        var sink = Sinks.many().unicast().onBackpressureBuffer();

//        handle through which subscriber will receive items
        var flux = sink.asFlux();

        /* ArrayList is not thread safe */
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for(int i=0; i<10000; i++){
            var j = i;
            CompletableFuture.runAsync(()->{
//                Try to emit method is not thread safe. It just tries to emit value. If we just want to try not guaranteed delivery.
                sink.tryEmitNext(j);//We are sharing sink with multiple threads. Multiple threads are trying to emit values
            });
        }
        Util.sleepSeconds(2);
        log.info("list size: {}", list.size());
    }

    /* Non Serialized - Value was trying to deliver message somehow I couldn't deliver. Because of some other thread trying to emit value that's why I couldn't */
    private static void demo02(){
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for(int i=0; i<10000; i++){
            var j = i;
            CompletableFuture.runAsync(()->{
                /* If we want Sink to be thread safe then we have to use emit versions with along emit failure handler
                *  Once we drop item into sink then beyond reactor will take it and give it to subscriber */
                sink.emitNext(j , (signalType, emitResult) ->{
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult); //If fail non serialized error comes then retry else no
                });
            });
        }
        Util.sleepSeconds(2);
        log.info("list size: {}", list.size());
    }
}
