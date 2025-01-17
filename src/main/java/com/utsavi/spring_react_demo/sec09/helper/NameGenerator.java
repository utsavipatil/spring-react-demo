package com.utsavi.spring_react_demo.sec09.helper;

import com.utsavi.spring_react_demo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private final List<String> redis = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(sink -> {
                    log.info("Generating names ...");
                    Util.sleepSeconds(1);
                    var name = Util.faker().name().firstName();
                    redis.add(name);
                    sink.next(name);
                })
                .startWith(redis) //While generating we read it from Cache first and then if require then only generate
                .cast(String.class);
    }
}
