package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {
    public static void main(String[] args) {
        Flux.<String>generate(sink -> sink.next(Util.faker().country().name()))
        .handle((item, sink)-> {
            sink.next(item);
            if(item.equalsIgnoreCase("canada")){
                sink.complete();
            }
        })
        .subscribe(Util.subscriber());
    }
}
