package com.utsavi.spring_react_demo.sec03.assigment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockPriceObserver implements Subscriber<Integer> {
    public static final Logger log = LoggerFactory.getLogger(StockPriceObserver.class);
    private Subscription subscription;
    private int quantity = 0;
    private int balance = 1000;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if(price < 90 && balance >= price){
           quantity++;
           balance -= price;
           log.info("Bought a stock: {} price with total quantity: {}, remaining balance: {}", price, quantity, balance);
        }else if(price > 110 && quantity > 0){
            balance += (price * quantity);
            quantity = 0;
            log.info("Sold a stock: {} price with total quantity: {}, remaining balance: {}", price, quantity, balance);
            subscription.cancel();
            log.info("Profit: {}",balance - 1000);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("Error ", throwable);
    }

    @Override
    public void onComplete() {
        log.info("Completed");
    }
}
