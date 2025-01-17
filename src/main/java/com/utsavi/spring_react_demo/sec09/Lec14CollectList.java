package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
*   To collect items received via Flux. Assuming we will have finite items !!!
* */
public class Lec14CollectList {
    public static void main(String[] args) {
        Flux.range(1,10)
//                .concatWith(Mono.error(new RuntimeException("oops")))
                .collectList()
                .subscribe(Util.subscriber());
    }
}
