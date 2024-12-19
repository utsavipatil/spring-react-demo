package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

/* To delay publisher creation */
public class Lec10MonoDefer {
    public static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.defer(() -> createPublisher());
//                .subscribe(Util.subscriber());
    }
    private static Mono<Integer> createPublisher(){
        log.info("creating Publisher");
        var list = List.of(1,2,3);
        Util.sleepSeconds(1);
        return Mono.just(sum(list));
    }
    private static int sum(List<Integer> list){
        log.info("Finding sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }
}
