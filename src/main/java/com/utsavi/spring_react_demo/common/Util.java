package com.utsavi.spring_react_demo.common;

import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {
    public static <T>Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }
    public static <T>Subscriber<T> subscriber(String name){
        return new DefaultSubscriber<T>(name);
    }
}
