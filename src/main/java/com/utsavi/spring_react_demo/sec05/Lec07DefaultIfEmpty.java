package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*  Similar to error handling
    Handling empty!
* */
public class Lec07DefaultIfEmpty {
    public static final Logger log = LoggerFactory.getLogger(Lec07DefaultIfEmpty.class);

    public static void main(String[] args) {
        Mono.empty()
                .defaultIfEmpty("fallback value of Mono") //In case of empty give me default value
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .filter(i -> i> 11)
                .defaultIfEmpty(50)
                .subscribe(Util.subscriber());
    }
}
