package com.utsavi.spring_react_demo.sec06.assignment;

import com.utsavi.spring_react_demo.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);
    private Flux<Order> orderFlux;

    public Flux<Order> orderStream(){
        if(Objects.isNull(orderFlux)){
            this.orderFlux = getOrderStream();
        }
        return this.orderFlux;
    }

    private Flux<Order> getOrderStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .doOnNext(order -> log.info("{}", order))
                .publish().refCount(2);
    }

    private Order parse(String message) {
        var array = message.split(":");
        return new Order(array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]));
    }
}