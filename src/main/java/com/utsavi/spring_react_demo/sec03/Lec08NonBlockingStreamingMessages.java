package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec03.client.ExternalServiceClient;

public class Lec08NonBlockingStreamingMessages {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        client.getNames().subscribe(Util.subscriber("sub1"));
        client.getNames().subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(6);
    }
}
