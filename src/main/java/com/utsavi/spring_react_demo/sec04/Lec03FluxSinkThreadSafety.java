package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

/* FluxSink is Thread Safety */
public class Lec03FluxSinkThreadSafety {
    public static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkThreadSafety.class);
    public static void main(String[] args) {
        demo2();
    }

    /* ArrayList is not Thread safe. Total list size should 10 * 1000 = 10000, but it always shows less */
    private static void demo1(){
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
          for(int i=0; i<1000; i++){
              list.add(i);
          }
        };
        for(int i=0; i<10; i++){
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }

    /* FluxSink is Thread Safe. Total list size = 10 * 1000 = 10000 */
    private static void demo2(){
        var list = new ArrayList<String>();
        var generatorConsumer = new NameGenerator();
        var flux = Flux.create(generatorConsumer);
        flux.subscribe(name -> list.add(name));

        Runnable runnable = () -> {
            for(int i=0; i<1000; i++){
                generatorConsumer.generate();
            }
        };
        for(int i=0; i<10; i++){
            new Thread(runnable).start();
        }
        Util.sleepSeconds(3);
        log.info("list size: {}", list.size());
    }
}
