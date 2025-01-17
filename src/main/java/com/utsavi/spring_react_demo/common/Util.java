package com.utsavi.spring_react_demo.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Util {

    public static final Faker faker = Faker.instance();
    public static final Logger log = LoggerFactory.getLogger(Util.class);

    public static <T>Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }
    public static <T>Subscriber<T> subscriber(String name){
        return new DefaultSubscriber<T>(name);
    }

    public static Faker faker(){
        return faker;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T>UnaryOperator <Flux<T>> fluxLogger(String name){
        return flux -> flux
                .doOnSubscribe(s -> log.info("Subscribing to {}",name))
                .doOnCancel(() -> log.info("Cancelling {}", name))
                .doOnComplete(() -> log.info("{} Completed", name));
    }
}
