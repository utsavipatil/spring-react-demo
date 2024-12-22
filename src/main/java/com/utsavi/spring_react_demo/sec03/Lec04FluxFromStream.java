package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {
    public static final Logger log = LoggerFactory.getLogger(Lec04FluxFromStream.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3);
        var stream = list.stream();

        /* In Java Stream can be operated ONLY ONCE, we can't operate same stream again */
        var flux = Flux.fromStream(() -> list.stream()); //Everytime it will create another stream

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
