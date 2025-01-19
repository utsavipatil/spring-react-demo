package com.utsavi.spring_react_demo.sec11.client;

public class ServerError extends RuntimeException{
    public ServerError() {
        super("Server Error");
    }
}
