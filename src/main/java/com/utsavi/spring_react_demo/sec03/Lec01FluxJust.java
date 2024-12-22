package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static final Logger log = LoggerFactory.getLogger(Lec01FluxJust.class);
    public static void main(String[] args) {
        Flux.just(1,2,3, true, "sam").subscribe(Util.subscriber());
    }
}
