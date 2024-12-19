package com.utsavi.spring_react_demo.sec01.publisher;

import com.github.javafaker.Faker;
import com.utsavi.spring_react_demo.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Faker faker;
    private static final int MAX_ITEMS = 10;
    private Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private int count;

    public SubscriptionImpl( Subscriber<? super String> subscriber){
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requested) {
        if(isCancelled){
            return;
        }
        log.info("Subscriber has requested {} many items" , requested);
        if(requested > MAX_ITEMS){
            this.subscriber.onError(new RuntimeException("Validation Failed"));
            this.isCancelled = true;
            return;
        }
        for(int i=0; i< requested && count < MAX_ITEMS; i++){
            count++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }
        if(count == MAX_ITEMS){
            log.info("no more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("subscriber has cancelled");
        this.isCancelled = true;
    }
}
