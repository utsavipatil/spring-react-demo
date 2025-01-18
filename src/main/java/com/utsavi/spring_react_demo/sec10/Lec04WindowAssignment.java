package com.utsavi.spring_react_demo.sec10;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec10.assignment.window.FileWriter;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {
    public static void main(String[] args) {
        var count= new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(count.getAndIncrement()))))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }
}
