package com.utsavi.spring_react_demo.sec03.client;

import com.utsavi.spring_react_demo.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/*To demo non-blocking IO with streaming messages Ensure that the external service is up & running!!! */
public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getNames(){
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }
    public Flux<Integer> getPriceChanges(){
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
