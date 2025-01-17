package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*  Ensure external service is up & running */
public class Lec13ConcatMap {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        Flux.range(1,10)
                .concatMap(id -> client.getProduct(id))
                //concurrency will allow n parallel calls at same time
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
