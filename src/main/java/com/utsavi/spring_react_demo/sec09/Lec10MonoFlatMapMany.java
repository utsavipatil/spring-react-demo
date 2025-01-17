package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.applications.Order;
import com.utsavi.spring_react_demo.sec09.applications.OrderService;
import com.utsavi.spring_react_demo.sec09.applications.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 *   Sequential non-blocking IO calls
 *   flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 *   Mono is supposed to be 1 item - what is the flatMAp return multiple items ?
 * */
public class Lec10MonoFlatMapMany {
    public static void main(String[] args) {
//        We have username & get all user orders
        UserService.getUserId("Mike")
                .flatMapMany(userId -> OrderService.getUserOrders(userId))
                .subscribe(Util.subscriber());
//        Inner publisher will have Many items, and we need to subscriber all items

        Util.sleepSeconds(3);
    }
}
