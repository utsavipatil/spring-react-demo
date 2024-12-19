package com.utsavi.spring_react_demo;

import com.utsavi.spring_react_demo.sec01.publisher.PublisherImpl;
import com.utsavi.spring_react_demo.sec01.publisher.SubscriptionImpl;
import com.utsavi.spring_react_demo.sec01.subscriber.SubscriberImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
1. Publisher does not produce data unless subscriber request for it
2. Publisher will produce only subscriber requested items. Publisher can also produce 0 item!!!
3. Subscriber can cancel subscription anytime. Producer should stop at that moment as subscriber is no longer interested consuming data
4. Producer can send error signal to indicate something is wrong,
* */

public class SpringReactDemoApplication {

	public static void main(String[] args) throws InterruptedException {
		demo4();
	}

	private static void demo1(){
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
	}
	private static void demo2() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
	}
	private static void demo3() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().cancel();
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
	}
	private static void demo4() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription().request(30);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
		Thread.sleep(2000);
		subscriber.getSubscription().request(3);
	}
}
