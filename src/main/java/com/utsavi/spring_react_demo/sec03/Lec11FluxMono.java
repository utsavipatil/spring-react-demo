package com.utsavi.spring_react_demo.sec03;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/* Convert Flux <--> Mono */
public class Lec11FluxMono {
    public static void main(String[] args) {
        /* Flux -> Mono */
        var mono = getUsername(1);
        save(Flux.from(mono));
        var flux = Flux.range(1,10);
        flux.next().subscribe(Util.subscriber());

        /* Mono -> Flux */
        Mono.from(flux).subscribe(Util.subscriber());
    }
    private static Mono<String> getUsername(int userId){
        return switch(userId){
            case 1 -> Mono.just("Dolly");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }
    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }
}
