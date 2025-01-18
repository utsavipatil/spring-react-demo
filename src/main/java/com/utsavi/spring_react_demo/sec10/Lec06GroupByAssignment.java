package com.utsavi.spring_react_demo.sec10;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec10.assignment.orderService.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06GroupByAssignment {
    public static final Logger log = LoggerFactory.getLogger(Lec06GroupByAssignment.class);

    public static void main(String[] args) {
        OrderService.orderStream()
                .filter(OrderService.canProcess())
                .groupBy(purchaseOrder -> purchaseOrder.category())
                .flatMap(groupFlux -> groupFlux.transform(OrderService.getProcessor(groupFlux.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
