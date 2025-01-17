package com.utsavi.spring_react_demo.sec09.applications;

import reactor.core.publisher.Mono;

import java.util.Map;

/*  Imagine payment-service, as an application, has an endpoint
 *   This is a client class which represents to call the endpoint (IO request)
 * */
public class PaymentService {
    private static final Map<Integer, Integer> userBalanceTable = Map.of(
            1, 100,
            2, 200,
            3, 300
    );

    public static Mono<Integer> getUserBalance(Integer userId) {
        return Mono.fromSupplier(() -> userBalanceTable.get(userId));
    }
}
