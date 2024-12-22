package com.utsavi.spring_react_demo.sec04;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec04.assignment.FileReadServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Lec09Assignment {
    public static final Logger log = LoggerFactory.getLogger(Lec09Assignment.class);

    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/readme.txt");
        var fileReaderService = new FileReadServiceImpl();
        fileReaderService.read(path)
//                .take(5
                .takeUntil(s -> s.equalsIgnoreCase("line17"))
                .subscribe(Util.subscriber());
    }
}
