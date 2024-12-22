package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            fluxSink.next(1);
            fluxSink.next(2);
            for(int i=0; i<10; i++){
                fluxSink.next(Util.faker().country().name());
            }
            String country = "";
            do{
                country = Util.faker().country().name();
                fluxSink.next(country);
            }while(!country.equals("Canada"));
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
