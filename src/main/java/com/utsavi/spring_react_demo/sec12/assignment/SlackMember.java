package com.utsavi.spring_react_demo.sec12.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class SlackMember {
    public static final Logger log = LoggerFactory.getLogger(SlackMember.class);
    private final String name;
    private Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    void setMessageConsumer(Consumer<String> messageConsumer){
        this.messageConsumer = messageConsumer;
    }
    public void says(String message){
        this.messageConsumer.accept(message);
    }
    void receives(String message){
        log.info(message);
    }
}
