package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08GenerateWithState {
    public static final Logger log = LoggerFactory.getLogger(Lec08GenerateWithState.class);

    public static void main(String[] args) {
        Flux.generate(()-> 0,
        (counter, sink) -> {
           var country = Util.faker().country().name();
           sink.next(country);
           counter++;
           if(counter == 10 || country.equalsIgnoreCase("canada")){
               sink.complete();
           }
           return counter;
        }).subscribe(Util.subscriber());
    }
}
