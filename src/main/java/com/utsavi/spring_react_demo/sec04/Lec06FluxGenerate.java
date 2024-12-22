package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/* fluxSink we can create item without worry about downstream demands - we have complete control in fluxSink
*  synchronousSink allow to emit only one value - If we want to generate multiple values it will give one more synchronousSink as per downstream demand
* */

/* Flux generate
- involkes the given lambda expression again and again based on downstream demand
- We can emit only one value at a time
- will stop when complete method is invoked
- will stop when error is invoked
- will stop downstream cancels
* */
public class Lec06FluxGenerate {
    public static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> { //synchronousSink allows max 1 value to emit
            log.info("invoked");
            synchronousSink.next(1);
//            synchronousSink.next(2);
            synchronousSink.error(new RuntimeException("oops"));
            synchronousSink.complete(); //can stop early
        })
        .take(4) //If we don't give take n then by default its infinite - int max
        .subscribe(Util.subscriber());
    }
}
