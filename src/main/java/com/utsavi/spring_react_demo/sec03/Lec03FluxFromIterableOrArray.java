package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {
    public static final Logger log = LoggerFactory.getLogger(Lec03FluxFromIterableOrArray.class);

    public static void main(String[] args) {
        var list = List.of("a","b","c");
        Flux.fromIterable(list).subscribe(Util.subscriber());

        Integer[] array = {1,2,3,4,5,6};
        Flux.fromArray(array).subscribe(Util.subscriber());
    }
}
