package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* To demo non-blocking IO. Ensure that the external service is up and coming */
public class Lec11NonBlockingIO {
    public static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("starting");
        for(int i=1; i<=5; i++){
            var output = client.getProductName(i)
                    .block(); //Thread will be blocked until we will get value - NOT USE !!!
//                    .subscribe(Util.subscriber());
            System.out.println(output);
        }

        Util.sleepSeconds(2);
    }
}
