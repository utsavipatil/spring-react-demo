package com.utsavi.spring_react_demo.sec11.client;

public class ClientError extends RuntimeException{
    public ClientError(){
        super("bad request");
    }
}
