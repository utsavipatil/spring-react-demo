package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/* If we have CompletableFuture already, then we can convert that into a Mono */
public class Lec08MonoFromFuture {
    public static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(() -> getName()).subscribe(Util.subscriber());
        Util.sleepSeconds(1);
    }

    /* Default CompletableFuture is not Lazy - we wil write line 15 manner */
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name");
            return Util.faker().name().firstName();
        });
    }
}
