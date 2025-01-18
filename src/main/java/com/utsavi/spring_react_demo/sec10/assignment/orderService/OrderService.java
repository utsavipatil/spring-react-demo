package com.utsavi.spring_react_demo.sec10.assignment.orderService;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class OrderService {
    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP = Map.of(
            "Kids" , KidsProcessing(),
            "Automotive", automotiveProcessing()
    );
    public record PurchaseOrder(String item, String category, Integer price){}

    public static Flux<PurchaseOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> new PurchaseOrder(Util.faker().commerce().productName(), Util.faker().commerce().department(), Util.faker().random().nextInt(10,100)));
    }

    private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing(){
        return flux -> flux
                .map(po -> new PurchaseOrder(po.item, po.category, po.price + 100));
    }
    private static UnaryOperator<Flux<PurchaseOrder>> KidsProcessing(){
        return flux -> flux
                .flatMap(po -> getFreeKidsOrder(po).flux().startWith(po));
    }
    private static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder order){
        return Mono.fromSupplier(() -> new PurchaseOrder(
           order.item() + "-FREE",
           order.category,
           0
        ));
    }
    public static Predicate<PurchaseOrder> canProcess(){
        return purchaseOrder -> PROCESSOR_MAP.containsKey(purchaseOrder.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category){
        return PROCESSOR_MAP.get(category);
    }
}
