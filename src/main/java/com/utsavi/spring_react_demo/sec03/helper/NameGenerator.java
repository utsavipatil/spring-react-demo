package com.utsavi.spring_react_demo.sec03.helper;

import com.utsavi.spring_react_demo.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {
    public static List<String> getNamesList(int count){
        return IntStream.range(1, count).mapToObj(i -> generateName()).toList();
    }
    private static String generateName(){
        Util.sleepSeconds(1);
        return Util.faker.name().firstName();
    }
    public static Flux<String> getNamesFlux(int count){
        return Flux.range(1, count).map(i -> generateName());
    }
}
