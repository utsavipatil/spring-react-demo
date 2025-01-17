package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
*   Ensure that external service is up and running !!!
* */
public class Lec12FluxFlatMapAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        Flux.range(1,10)
                .flatMap(id -> client.getProduct(id),3)
                //concurrency will allow n parallel calls at same time
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
