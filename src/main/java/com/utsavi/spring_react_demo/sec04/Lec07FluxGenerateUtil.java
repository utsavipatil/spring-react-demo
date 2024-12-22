package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUtil {
    public static final Logger log = LoggerFactory.getLogger(Lec07FluxGenerateUtil.class);

    public static void main(String[] args) {
        way1();
    }

    private static void way1(){
        Flux.generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
            if(country.equalsIgnoreCase("canada")){
                synchronousSink.complete();
            }
        })
        .subscribe(Util.subscriber());
    }

    private static void way2(){
        Flux.generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .takeUntil(name -> name.equals("Canada"))
                .subscribe(Util.subscriber());
    }
}
