package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {
    public static void main(String[] args) {
        Flux.empty().subscribe(Util.subscriber());
        Flux.error(new RuntimeException("oops")).subscribe(Util.subscriber());
    }
}
