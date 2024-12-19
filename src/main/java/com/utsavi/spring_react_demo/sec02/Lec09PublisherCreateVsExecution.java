package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/*  Creating Publisher is a lightweight operation
    Executing time-consuming business logic should be delayed
* */
public class Lec09PublisherCreateVsExecution {
    public static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) throws InterruptedException {
        getName();
    }

    /* We are just creating publisher we are not doing operation (when subscribe then only run) */
    private static Mono<String> getName(){
        log.info("Entered the method");
        return Mono.fromSupplier(() -> { //This is business logic, These need to be lazy
            log.info("generating name");
            Util.sleepSeconds(3000);
            return Util.faker().name().firstName();
        });
    }
}
