package com.utsavi.spring_react_demo.sec09.assignment;

import com.utsavi.spring_react_demo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<Product> getProduct(int id){
        return Mono.zip(getProductName(id), getReview(id), getPrice(id))
                .map(tuple -> new Product(tuple.getT1(), tuple.getT2(), tuple.getT3()));
    }

    private Mono<String> getPrice(Integer id){
        return get("/demo05/price/" + id);
    }
    private Mono<String> getProductName(Integer id){
        return get("/demo05/product/" + id);
    }
    private Mono<String> getReview(Integer id){
        return get("/demo05/review/" + id);
    }
    private Mono<String> get(String path){
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
