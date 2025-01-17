package com.utsavi.spring_react_demo.sec09;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec09.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec02StartWithUseCase {
    public static final Logger log = LoggerFactory.getLogger(Lec02StartWithUseCase.class);
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();
        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Same"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Mike"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Jake"));
    }
}
