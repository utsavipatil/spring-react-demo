package com.utsavi.spring_react_demo.sec13;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/* Context is immutable map. We can append additional info !!! */
public class Lec02ContextAppendUpdate {
    public static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
//                .contextWrite(context -> Context.empty()) //Overwrite context
//                .contextWrite(context -> context.delete("c"))
                .contextWrite(context -> context.put("user",context.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("a", "b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user", "Dolly"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(contextView -> {
            log.info("{}", contextView);
            if(contextView.hasKey("user")){
                return Mono.just("Welcome %s !!!".formatted(contextView.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
