package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUserName(1).subscribe(Util.subscriber());
        getUserName(2).subscribe(Util.subscriber());
        getUserName(3).subscribe(Util.subscriber());
        getUserName(1).subscribe(Util.subscriber());

        //If error is expecting and handler don't add then throw "Operator called default onErrorDropped" error
        getUserName(3).subscribe(s -> System.out.println(s),
                                        error -> {});
    }
    private static Mono<String> getUserName(int userId){
        return switch(userId){
            case 1 -> Mono.just("Utsavi");
            case 2 -> Mono.empty(); //Null
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }
}
