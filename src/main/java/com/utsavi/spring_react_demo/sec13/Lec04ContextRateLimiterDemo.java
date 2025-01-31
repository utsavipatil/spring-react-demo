package com.utsavi.spring_react_demo.sec13;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec13.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.context.Context;

/* Ensure that external service is up and running */
public class Lec04ContextRateLimiterDemo {
    public static final Logger log = LoggerFactory.getLogger(Lec04ContextRateLimiterDemo.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 0; i < 10; i++) {
            client.getBook()
                    .contextWrite(Context.of("user","Dolly"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }

        Util.sleepSeconds(5);
    }
}
