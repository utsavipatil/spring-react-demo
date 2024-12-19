package com.utsavi.spring_react_demo.sec02;

/* If we don't have terminal operator, then stream operators won't execute */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.apache.logging.log4j.ThreadContext.peek;

public class Lec01LazyStream {
    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {
        Stream.of(1)
            .peek(i -> log.info("received : {}", i))
            .toList();
    }
}
