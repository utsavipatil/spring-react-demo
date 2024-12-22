package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec03.assigment.StockPriceObserver;
import com.utsavi.spring_react_demo.sec03.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* Ensure External Service is up running */
public class Lec12Assignment {
    public static final Logger log = LoggerFactory.getLogger(Lec12Assignment.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();
        client.getPriceChanges().subscribe(subscriber);
        Util.sleepSeconds(20);
    }
}
