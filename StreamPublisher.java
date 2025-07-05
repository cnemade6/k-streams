package com.example.kafkaStreamProducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StreamPublisher {

	@Autowired
	private KafkaTemplate<String, String> template;
	
	public void publishStreamData( String event) {
		template.send("sourceTopic", event);
		System.out.println("event sent successfully on sourceTopic "+ event);
	}
}
