package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.applications.PaymentService;
import com.utsavi.spring_react_demo.sec09.applications.UserService;
import reactor.core.publisher.Mono;

/*  Sequential non-blocking IO calls!!!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
* */
public class Lec09MonoFlatMap {
    public static void main(String[] args) {
//        We have username & Get user account balance
        UserService.getUserId("Mike")
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());
        //FlatMap subscribe to inner publisher
    }
}
