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
@EmbeddedKafka(topics = { "kp-hello-topic", "kp-hello-test-topic" , brokerProperties = { "listeners=PLAINTEXT://kafka:9092" })
public class KafkaStreamExampleApplicationTestsIT {

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
		System.out.println("=========Before send=====");
		stream.msgPublisher().send(MessageBuilder.withPayload("Hello1234").build());
		System.out.println("=========After send=====");
		try {
			System.out.println("=========Before latch=====");
			listener.getLatch().await(15, TimeUnit.SECONDS);
			System.out.println("=========After latch=====");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("=========error====="+e);
			e.printStackTrace();
		}
		System.out.println("Done Testing...");
	}

}
