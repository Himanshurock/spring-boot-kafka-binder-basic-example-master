# spring-boot-kafka-binder-basic-example
A sample Spring cloud Kafka binder example, primarily focusing on Running Embedded Kafka for JUNIT

## Run as standalone application.

- Start zookeeper server.

- Start Kafka node/configure application.yml to point to kafka node.

- Run application

```
mvn spring-boot:run

```
- You can send message to Kafka by invoking below REST endpoint from browser

```
http://localhost:8080/hello?name=karthik

```

## Run as JUNIT

<p> To run the application for junit, execute below command.

```
mvn clean test

```

**Note:**

- When added `spring-cloud-stream-test-support` dependency message was not getting neither delivered or received, hence removed the dependence.

```
    <!-- <dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-stream-test-support</artifactId>
			<scope>test</scope>
		 </dependency> -->

```
 
- In the example since wanted use `@EmbeddedKafka` annotation, I upgraded `spring-kafka` version to `2.1.5.RELEASE`. Due to `spring-kafka` version upgrade, needed to upgrade `kafka-client` version as well, please refer [version compatibility matrix.][1] else you would end up with `classnotfound or classdef` errors

```
		<spring-kafka.version>2.1.5.RELEASE</spring-kafka.version>
		<kafka.version>1.0.1</kafka.version>
		
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka-clients</artifactId>
			<version>${kafka.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.kafka</groupId>
			<artifactId>kafka_2.11</artifactId>
			<version>${kafka.version}</version>
		</dependency>

```

 - Configuration required to run without upgrade.
   - Create Instance of kafka embedded.

      ```
	     @ClassRule
	     public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1);

      ```

    - Since Embedded kafka generates random port we need to read and set broker URL.
      ```	
	     @BeforeClass
         public static void setupEnvironment() {
             System.setProperty("spring.cloud.stream.kafka.binder.brokers", embeddedKafka.getBrokersAsString());
		      System.setProperty("spring.cloud.stream.kafka.binder.zkNodes", embeddedKafka.getZookeeperConnectionString());
	      }

      ```

[1]: http://projects.spring.io/spring-kafka/