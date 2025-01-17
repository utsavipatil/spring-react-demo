package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.applications.*;
import com.utsavi.spring_react_demo.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Mono;

import java.util.List;

/*
 *   Get all users and build 1 object as shown here.
 *   record UserInformation(Integer userId, String username, Integer balance, List<Order> orders){}
 * */
public class Lec16Assignment {
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        UserService.getAllUsers()
                .flatMap(user -> getUserInformation(user))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(PaymentService.getUserBalance(user.id()),
                        OrderService.getUserOrders(user.id()).collectList())
                .map(t -> new UserInformation(user.id(), user.username(), t.getT1(), t.getT2()));
    }
}
