package com.example.kafkaStreamProducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.kafkaStreamProducer.util.Handset;

@Configuration 
public class StreamProducerConfig {

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String,Object> configMap = new HashMap();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configMap);
		
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){
		
		return new KafkaTemplate<String, String>(producerFactory());
		
	}
}
