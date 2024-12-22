package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {
    public static final Logger log = LoggerFactory.getLogger(Lec05FluxRange.class);

    public static void main(String[] args) {
//        Flux.range(3,10).subscribe(Util.subscriber());
        Flux.range(1,10).map(i -> Util.faker.name().firstName()).subscribe(Util.subscriber());
    }
}
