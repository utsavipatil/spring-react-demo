package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
//        Mono<String> mono = Mono.just("Utsavi"); //Publisher who gives "Utsavi" value
        var mono = Mono.just("Utsavi");
        System.out.println(mono); //Till we don't subscribe publisher won't publish message

        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);

        //Adding these will have no effect as producer already sent complete (Mono)
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }

}
