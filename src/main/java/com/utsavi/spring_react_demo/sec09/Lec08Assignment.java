package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.assignment.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* Ensure external service is up & running */
public class Lec08Assignment {
    public static final Logger logger = LoggerFactory.getLogger(Lec08Assignment.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for(int i=0; i<=10; i++){
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(10);
    }
}
