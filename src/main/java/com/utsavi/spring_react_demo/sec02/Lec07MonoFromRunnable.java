package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/* Emitting empty after some method invocation */
public class Lec07MonoFromRunnable {
    private static final Logger log = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        getProductName(1).subscribe(Util.subscriber());
        getProductName(2).subscribe(Util.subscriber());
    }
    public static Mono<String> getProductName(int productId){
        if(productId == 1){
            return Mono.fromSupplier(()-> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(()-> notifyBusiness(productId));
    }
    public static void notifyBusiness(int productId){
        log.info("Notifying business on unavailable product {} ", productId);
    }
}
