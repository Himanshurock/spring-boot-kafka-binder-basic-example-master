package com.kp.swasthik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.kp.swasthik.streams.HelloStream;

@SpringBootApplication
@EnableBinding(HelloStream.class)
public class KafkaStreamExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamExampleApplication.class, args);
	}
}
