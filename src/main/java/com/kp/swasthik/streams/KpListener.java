package com.kp.swasthik.streams;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KpListener {
	
	@StreamListener(HelloStream.IN_CHANNEL)
	public void handleMessage(@Payload String message) {
		System.out.println(message);
	}

}
