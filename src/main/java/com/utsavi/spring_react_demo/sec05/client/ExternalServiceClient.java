package com.utsavi.spring_react_demo.sec05.client;

import com.utsavi.spring_react_demo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId){
        var defaultPath = "/demo03/product/" + productId;
        var timeoutPath = "/demo03/timeout-fallback/product/" + productId;
        var emptyPath = "/demo03/empty-fallback/product/" + productId;

        return getProductName(defaultPath)
                .timeout(Duration.ofSeconds(2), getProductName(timeoutPath))
                .switchIfEmpty(getProductName(emptyPath));
    }

    private Mono<String> getProductName(String path){
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }

}
