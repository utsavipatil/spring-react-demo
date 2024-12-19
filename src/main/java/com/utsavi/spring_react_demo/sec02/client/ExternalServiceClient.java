package com.utsavi.spring_react_demo.sec02.client;

import com.utsavi.spring_react_demo.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next(); //next to convert Flux -> Mono Will take first string of stream of response
    }
}
