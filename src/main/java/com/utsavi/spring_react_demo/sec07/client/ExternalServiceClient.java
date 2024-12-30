package com.utsavi.spring_react_demo.sec07.client;

import com.utsavi.spring_react_demo.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class ExternalServiceClient extends AbstractHttpClient {

    public static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(value -> log.info("next: {}", value))
                .next()
                .publishOn(Schedulers.boundedElastic()); //Whatever data is generated it will give to publishOn and concentrate on IO task
    }

}
