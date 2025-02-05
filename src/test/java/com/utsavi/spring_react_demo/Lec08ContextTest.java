package com.utsavi.spring_react_demo;

import com.utsavi.spring_react_demo.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {
    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(contextView -> {
            if(contextView.hasKey("user")){
                return Mono.just("Welcome %s !!!".formatted(contextView.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    @Test
    public void welcomeMessageTest(){
        var options = StepVerifierOptions.create().withInitialContext(Context.of("user","Dolly"));
        StepVerifier.create(getWelcomeMessage(),options)
                .expectNext("Welcome Dolly !!!");
    }

    @Test
    public void unauthenticated(){
        var options = StepVerifierOptions.create().withInitialContext(Context.empty());
        //If we don't pass options, it is called Empty context
        StepVerifier.create(getWelcomeMessage(), options)
                .expectErrorMessage("unauthenticated")
                .verify();
    }
}
