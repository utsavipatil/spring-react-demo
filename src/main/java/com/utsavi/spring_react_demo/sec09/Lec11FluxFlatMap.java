package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.applications.OrderService;
import com.utsavi.spring_react_demo.sec09.applications.User;
import com.utsavi.spring_react_demo.sec09.applications.UserService;

/*  Sequential non-blocking IO calls
    FlatMap is used to flatten the inner publisher / to subscribe to the inner publisher
* */
public class Lec11FluxFlatMap {
    public static void main(String[] args) {
        /* Get all orders from order service */
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(order -> OrderService.getUserOrders(order), 1)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
