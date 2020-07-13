package com.kp.swasthik;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface HelloTestStream {

	String IN_CHANNEL = "kp-in-test-channel";
	String OUT_CHANNEL = "kp-out-test-channel";

	@Output(OUT_CHANNEL)
	MessageChannel msgPublisher();

	@Input(IN_CHANNEL)
	SubscribableChannel rcvStream();
	
}
