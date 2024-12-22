package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/* Flux create does NOT check the downstream demand by default !!! It is by design ! */
public class Lec04FluxCreateDownstreamDemo {
    public static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemo.class);
    public static void main(String[] args) {
       produceOnDemand();
    }

    private static void produceEarly(){
        var subscriber = new SubscriberImpl();

        /* Producer did all work but Subscriber not did */
        Flux.<String>create(fluxSink -> {
            for(int i=0; i<10; i++){
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                fluxSink.next(name);
            }
        }).subscribe(subscriber);

//        subscriber.getSubscription().cancel(); //Not requested still generating
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }

    private static void produceOnDemand(){
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for(int i=0; i<request && !fluxSink.isCancelled(); i++){
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);

        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
