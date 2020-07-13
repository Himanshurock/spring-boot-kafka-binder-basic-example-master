package com.kp.swasthik;

import java.util.concurrent.CountDownLatch;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KpTestListener {
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	
	@StreamListener(HelloTestStream.IN_CHANNEL)
	public void handleMessage(@Payload String message) {
		System.out.println("=====msg======"+message);
		latch.countDown();
	}
	
	public CountDownLatch getLatch() {
		return latch;
	}

}
