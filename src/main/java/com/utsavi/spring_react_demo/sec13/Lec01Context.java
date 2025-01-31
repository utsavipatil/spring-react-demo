package com.utsavi.spring_react_demo.sec13;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/* Context is for providing metadata about the request (similar to HTTP headers) */
public class Lec01Context {
    public static final Logger log = LoggerFactory.getLogger(Lec01Context.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user", "Dolly"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(contextView -> {
            if(contextView.hasKey("user")){
                return Mono.just("Welcome %s !!!".formatted(contextView.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
