package com.utsavi.spring_react_demo.sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InventoryService implements OrderProcessor{

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentQuantity = db.getOrDefault(order.category(), 500);
        var updatedQuantity = currentQuantity - order.quantity();
        db.put(order.category(), updatedQuantity);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
