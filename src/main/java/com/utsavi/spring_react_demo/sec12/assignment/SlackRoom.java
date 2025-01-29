package com.utsavi.spring_react_demo.sec12.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {
    private static final Logger log = LoggerFactory.getLogger(SlackRoom.class);

    private final String slackRoomName;
    private final Sinks.Many<SlackMessage> sink;
    private final Flux<SlackMessage> flux;

    public SlackRoom(String slackRoomName){
        this.slackRoomName = slackRoomName;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void addMember(SlackMember slackMember){
        log.info("{} joined the room {}", slackMember.getName(), this.slackRoomName);
        this.subscribeToRoomMessages(slackMember);
        slackMember.setMessageConsumer(message -> this.postMessage(slackMember.getName(), message));
    }
    private void subscribeToRoomMessages(SlackMember slackMember){
        this.flux
                //Member won't see own messages
                .filter(slackMessage -> !slackMessage.sender().equals(slackMember.getName()))
                .map(slackMessage -> slackMessage.formatForDelivery(slackMember.getName()))
                .subscribe(message -> slackMember.receives(message));
    }
    private void postMessage(String sender, String message){
        this.sink.tryEmitNext(new SlackMessage(sender, message));
    }
}
