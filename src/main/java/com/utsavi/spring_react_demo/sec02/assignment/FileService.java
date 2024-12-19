package com.utsavi.spring_react_demo.sec02.assignment;

import reactor.core.publisher.Mono;

public interface FileService {
    Mono<String> read(String fileName);
    Mono<Void> write(String fileName, String content);
    Mono<Void> delete(String fileName);
}
