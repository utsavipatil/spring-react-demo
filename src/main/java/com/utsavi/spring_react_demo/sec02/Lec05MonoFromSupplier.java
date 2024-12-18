package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);
    public static void main(String[] args) {
        var list = List.of(1 , 2 , 3);
        /* just = Whatever you want to send that value is already in memory else you have to perform
        * if Mono.just(sum(list)) -> Though subscriber is not subscribe yet, it will still compute
        * Solution Mono.fromSupplier -> value is not created until someone subscribes to the Mono, allowing for lazy evaluation
        * Supplier -> functional interface in Java that represents a function with no arguments that returns a result
        * */
//        Mono.just(sum(list)).subscribe(Util.subscriber());
        Mono.fromSupplier(()-> sum(list)).subscribe(Util.subscriber());
    }
    private static int sum(List<Integer> list){
        log.info("Finding sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
