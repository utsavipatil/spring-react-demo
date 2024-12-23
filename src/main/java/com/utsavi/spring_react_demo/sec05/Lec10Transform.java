package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {
    public static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name) {
    }

    record PurchaseOrder(String productName, int price, int quantity) {
    }

    public static void main(String[] args) {
        boolean isDebugEnabled = false;
        getCustomers()
                .transform(isDebugEnabled ? addDebugger() : Function.identity()) //Function,identity() return as it is
                .subscribe();

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe();
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 5).map(i -> new PurchaseOrder(Util.faker.commerce().productName(), i, i * 10));
    }

    //Function<Flux<T>, Flux<T>> - Function having input & output same can represent by Unary Operator - UnaryOperator<Flux<T>>
    private static <T> UnaryOperator<Flux<T>> addDebugger(){
        return flux -> flux
                .doOnNext(i -> log.info("Received: {}", i))
                .doOnComplete(() -> log.info("Completed"))
                .doOnError(error -> log.error("Error ", error));
    }

}
