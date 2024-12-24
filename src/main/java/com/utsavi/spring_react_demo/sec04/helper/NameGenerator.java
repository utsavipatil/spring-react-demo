package com.utsavi.spring_react_demo.sec04.helper;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {
    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        System.out.println("got a flux sink");
        this.sink = stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.faker().name().firstName());
    }
}
