package com.utsavi.spring_react_demo.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {

    public static final Faker faker = Faker.instance();

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
}
