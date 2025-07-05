package com.example.kafkaStreamProducer.util;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes.WrapperSerde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class HandsetSerde extends WrapperSerde<Handset> {

	public HandsetSerde() {
		super(new JsonSerializer<>(), new JsonDeserializer<>(Handset.class));
		
	}

	
}
