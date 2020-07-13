package com.kp.swasthik;


import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EnableBinding(HelloTestStream.class)
@EmbeddedKafka(topics = { "kp-hello-topic", "kp-hello-test-topic" })
public class KafkaStreamExampleApplicationIT {

//	@ClassRule
//	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1);
	
	@Autowired
	private HelloTestStream stream;
	
	@Autowired
	private KpTestListener listener;
	
//	@BeforeClass
//	public static void setupEnvironment() {
//		System.setProperty("spring.cloud.stream.kafka.binder.brokers", embeddedKafka.getBrokersAsString());
//		System.setProperty("spring.cloud.stream.kafka.binder.zkNodes", embeddedKafka.getZookeeperConnectionString());
//	}
//	
	@Test
	public void sendTest() {
		stream.msgPublisher().send(MessageBuilder.withPayload("HelloTest1234").build());
		System.out.println("=====HelloTest1234 sent======");
		try {
			listener.getLatch().await(15, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done Testing...");
	}

}
