package com.utsavi.spring_react_demo.sec02;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec02.assignment.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record Lec12Assigment() {
    public static final Logger log = LoggerFactory.getLogger(Lec12Assigment.class);

    public static void main(String[] args) {
        var fileService = new FileServiceImpl();
        fileService.write("file.txt", "This is a test file").subscribe(Util.subscriber());
        fileService.read("file.txt").subscribe(Util.subscriber());
        fileService.delete("file.txt").subscribe(Util.subscriber());
    }
}
