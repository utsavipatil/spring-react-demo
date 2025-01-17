package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.helper.Kayak;

public class Lec06MergeUseCase {
    public static void main(String[] args) {
        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
