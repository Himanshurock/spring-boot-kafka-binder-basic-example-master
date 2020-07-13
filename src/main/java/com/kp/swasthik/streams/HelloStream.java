package com.kp.swasthik.streams;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface HelloStream {
	
	String IN_CHANNEL = "kp-in-channel";
	String OUT_CHANNEL = "kp-out-channel";

	@Output(OUT_CHANNEL)
	MessageChannel msgPublisher();

	@Input(IN_CHANNEL)
	SubscribableChannel rcvStream();

}
