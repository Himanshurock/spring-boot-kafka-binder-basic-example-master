package com.kp.swasthik.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kp.swasthik.streams.HelloStream;

@RestController
public class HelloRest {

	@Autowired
	private HelloStream stream;

	@GetMapping("/hello")
	public String sayHello(@RequestParam("name") String name) {
		stream.msgPublisher().send(MessageBuilder.withPayload(name).build());
		return "Hello " + name;
	}

}
