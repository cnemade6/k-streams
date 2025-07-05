package com.example.kafkaStreamProducer.service;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.stereotype.Component;

import com.example.kafkaStreamProducer.util.Handset;

@Component
public class KStreamProcessor {

	private String nokiaEventHolder= "NokiaEventTopic";
	
	
	public void process(KStream<String, Handset>stream) {
		stream.filter(new Predicate<String, Handset>() {

			@Override
			public boolean test(String key, Handset value) {
				if(value!= null && "Nokia".equals(value.getCompany())) {
					return true;
				}
				return false;
			}
			
			
			
		}).to(nokiaEventHolder);
		
		System.out.println("nokia events sent to NokiaEventTopic...");
	}
}
