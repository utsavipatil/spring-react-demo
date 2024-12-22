package com.utsavi.spring_react_demo.sec04.assignment;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

/*  - do the work only when it is subscribed
    - do work based on demand (read line by line)
    - stop producing when subscriber cancels
    - produce only requested items
    - file should be closed once done
* */
public interface FileReaderService {
    Flux<String> read(Path path);
}
