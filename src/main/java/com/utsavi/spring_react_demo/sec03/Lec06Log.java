package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06Log {
    public static final Logger log = LoggerFactory.getLogger(Lec06Log.class);

    public static void main(String[] args) {
        Flux.range(1,3)
                .log("range-map") //log works subscriber for publisher and vice versa to track methods invocation
                .map(i -> Util.faker.name().firstName()) //Log will track between publisher & map
                .log("map-subscribed")
                .subscribe(Util.subscriber());
    }
}
