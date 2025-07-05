package com.example.kafkaStreamProducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication


public class KafkaStreamProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamProducerApplication.class, args);
	}

}
