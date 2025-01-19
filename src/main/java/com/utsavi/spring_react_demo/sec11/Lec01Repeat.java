package com.utsavi.spring_react_demo.sec11;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
*   Repeat operator simply resubscribes when it sees complete signal
*   it does not like error signal
* */
public class Lec01Repeat {
    public static void main(String[] args) {
        Flux.just(1,2,3)
                        .repeat(3)
                        .subscribe(Util.subscriber());
//        demo04();
//        Util.sleepSeconds(10);
    }
    private static Mono<String> getCountryName(){
        return Mono.fromSupplier(()-> Util.faker().country().name());
    }
    private static void demo01(){
        getCountryName().repeat(3)
                .subscribe(Util.subscriber());
    }
    private static void demo02(){
        getCountryName()
                .repeat()
                .takeUntil(country -> country.equalsIgnoreCase("Singapore")) //repeat will cancel if subscriber sends cancel request
                .subscribe(Util.subscriber());
    }
    private static void demo03(){
        var atomicInteger = new AtomicInteger(0);
        getCountryName()
                .repeat(()-> atomicInteger.incrementAndGet() < 3)
                .subscribe(Util.subscriber());
    }
    private static void demo04(){
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2)).take(2))
                //repeat after 2 sec and take only 2
                .subscribe(Util.subscriber());
    }
    private static void demo00(){
        var mono = Mono.fromSupplier(()-> Util.faker().country().name()); //Real life this will non-blocking IO Operations
        var subscriber = Util.subscriber();
        mono
                .repeat(3) //We will get more than 1 item that's why it converts to Flux<String>
                //Repeat will repeat only after complete signal
                .subscribe(subscriber);

        //We can not use in this way, here till we get first response, we may get 2nd 3rd operation due to non-blocking IO call - we are sending 3 concurrent request
        for(int i=0; i<3; i++){
            mono.subscribe(subscriber);
        }
    }
}
