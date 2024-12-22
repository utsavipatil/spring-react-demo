package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec01.subscriber.SubscriberImpl;
import com.utsavi.spring_react_demo.sec03.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*  List - send data once whole list is generated
    Flux - send data immediately push to subscriber when produce from producer, it won't block to generate whole list
* */
public class Lec07FluxVsList {
    public static final Logger log = LoggerFactory.getLogger(Lec07FluxVsList.class);

    public static void main(String[] args) {
        var list = NameGenerator.getNamesList(10);
        System.out.println(list);

        NameGenerator.getNamesFlux(10).subscribe(Util.subscriber());

        var subscriber = new SubscriberImpl();
        NameGenerator.getNamesFlux(10).subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
    }
}
