package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCollable {
    public static void main(String[] args) {
        var list = List.of(1, 2, 3);
        /* Supplier don't throw Exception as part of method signature - It can throw RuntimeException but doesn't have checked exception
        * Callable - It throws Exception as part of method signature
        * */
        Mono.fromSupplier(()-> {
            try {
                return sum(list);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).subscribe(Util.subscriber());
        Mono.fromCallable(()-> sum(list)).subscribe(Util.subscriber());
    }
    private static int sum(List<Integer> list) throws Exception{
        return list.stream().mapToInt(i->i).sum();
    }
}
