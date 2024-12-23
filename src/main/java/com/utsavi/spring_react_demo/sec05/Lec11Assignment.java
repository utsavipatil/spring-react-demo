package com.utsavi.spring_react_demo.sec05;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec05.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11Assignment {
    public static final Logger log = LoggerFactory.getLogger(Lec11Assignment.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for(int i=1; i<=4; i++){
            client.getProductName(i).subscribe(Util.subscriber());
        }

        Util.sleepSeconds(5);
    }


}
