package com.utsavi.spring_react_demo.sec06;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

/* share FluxSink with multiple thread and it keeps on emitting data. Sametime I want to have multiple subscriber to observe data
* One Publisher -> Multiple Subscribers
* */
public class Lec05FluxCreateIssueFix {
    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        //Default Cold publishers - When new fluxSink called it lost previous fluxSink That's why Flux.create is Single Subscriber approach
//        flux.subscribe(Util.subscriber("sub2"));

        for(int i=0; i<5; i++){
            generator.generate();
        }
    }
}
