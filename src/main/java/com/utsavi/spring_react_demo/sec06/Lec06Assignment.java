package com.utsavi.spring_react_demo.sec06;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec06.assignment.ExternalServiceClient;
import com.utsavi.spring_react_demo.sec06.assignment.InventoryService;
import com.utsavi.spring_react_demo.sec06.assignment.RevenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec06Assignment {
    public static final Logger log = LoggerFactory.getLogger(Lec06Assignment.class);

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe((i) -> inventoryService.consume(i));
        client.orderStream().subscribe((i) -> revenueService.consume(i));

        inventoryService.stream().subscribe(Util.subscriber("inventory"));
        revenueService.stream().subscribe(Util.subscriber("Revenue"));

        Util.sleepSeconds(30);
    }
}
