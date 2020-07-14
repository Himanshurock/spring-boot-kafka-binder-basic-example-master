package com.kp.swasthik;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EnableBinding(HelloTestStream.class)
@EmbeddedKafka(topics = { "kp-hello-topic", "kp-hello-test-topic" })
public class KafkaStreamExampleApplicationIT {

	@Autowired
	private HelloTestStream stream;
	
	@Autowired
	private KpTestListener listener;

	@Test
	public void sendTest() {
		String msg = "HelloTest123";
		stream.msgPublisher().send(MessageBuilder.withPayload(msg).build());
		System.out.println("=====Input msg sent======"+msg);
		try {
			listener.getLatch().await(15, TimeUnit.SECONDS);
			assertThat(listener.getLatch().getCount()).isEqualTo(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done Testing...");
	}
}
