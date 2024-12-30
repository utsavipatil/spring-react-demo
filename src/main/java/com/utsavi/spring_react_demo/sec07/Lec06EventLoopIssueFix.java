package com.utsavi.spring_react_demo.sec07;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec07.client.ExternalServiceClient;

public class Lec06EventLoopIssueFix {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for(int i=0; i<5; i++){
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(20);
    }

    private static String process(String input){
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
