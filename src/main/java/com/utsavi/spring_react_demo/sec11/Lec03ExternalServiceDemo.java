package com.utsavi.spring_react_demo.sec11;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec11.client.ExternalServiceClient;
import com.utsavi.spring_react_demo.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {
    public static final Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);
    public static void main(String[] args) {
        retry();
        Util.sleepSeconds(25);
    }
    private static void repeat(){
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("India"))
                .subscribe(Util.subscriber());
    }
    private static void retry(){
        var client = new ExternalServiceClient();
        client.getProductName(2) //1 - bad request
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }
    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("Retrying {}", rs.failure().getMessage()));
    }
}
