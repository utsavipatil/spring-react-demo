package com.utsavi.spring_react_demo.sec12;

import com.utsavi.spring_react_demo.common.Util;
import com.utsavi.spring_react_demo.sec12.assignment.SlackMember;
import com.utsavi.spring_react_demo.sec12.assignment.SlackRoom;

public class Lec08SlackAssignment {
    public static void main(String[] args) {
        var room = new SlackRoom("Reactor");
        var Dolly = new SlackMember("Dolly");
        var Prashant = new SlackMember("Prashant");
        var Parth = new SlackMember("Parth");

        //add 2 members
        room.addMember(Dolly);
        room.addMember(Prashant);

        Dolly.says("Hi all...");
        Util.sleepSeconds(4);

        Prashant.says("Hey!");
        Util.sleepSeconds(4);

        room.addMember(Parth);
        Parth.says("Hey guys.. glad to be here... ");
    }
}
